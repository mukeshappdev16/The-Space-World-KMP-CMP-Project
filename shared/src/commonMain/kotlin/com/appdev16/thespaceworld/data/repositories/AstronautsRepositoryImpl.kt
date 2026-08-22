package com.appdev16.thespaceworld.data.repositories

import com.appdev16.thespaceworld.data.database.dao.AstronautDao
import com.appdev16.thespaceworld.data.database.entities.AstronautEntity
import com.appdev16.thespaceworld.data.mappers.toDomain
import com.appdev16.thespaceworld.data.mappers.toEntity
import com.appdev16.thespaceworld.data.remote.AstronautsRemoteDataSource
import com.appdev16.thespaceworld.domain.modal.astronauts.Astronaut
import com.appdev16.thespaceworld.domain.repositories.AstronautsRepository
import com.appdev16.thespaceworld.util.NetworkError
import com.appdev16.thespaceworld.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Implementation of [AstronautsRepository] that handles astronaut data operations.
 *
 * This repository manages the synchronization between the [AstronautsRemoteDataSource]
 * and the local [AstronautDao], ensuring that the application has access to
 * astronaut data even when offline.
 *
 * @param remoteDataSource The source for fetching astronaut data from the network.
 * @param astronautDao The data access object for local astronaut persistence.
 */
class AstronautsRepositoryImpl(
    private val remoteDataSource: AstronautsRemoteDataSource,
    private val astronautDao: AstronautDao
) : AstronautsRepository {

    /**
     * Returns a stream of all astronauts available in the local database.
     * The data is mapped from [AstronautEntity] to the [Astronaut] domain model.
     */
    override fun getAstronauts(): Flow<List<Astronaut>> {
        return astronautDao.getAstronauts().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    /**
     * Returns a stream of a single astronaut identified by [id].
     * Returns null if no astronaut with the given ID is found in the local database.
     */
    override fun getAstronautById(id: Int): Flow<Astronaut?> {
        return astronautDao.getAstronautById(id).map { it?.toDomain() }
    }

    /**
     * Synchronizes the local database with astronaut data from the remote source.
     *
     * Fetches a list of astronauts based on the provided [limit] and [offset].
     * If [offset] is 0, it assumes a fresh sync and clears existing local records.
     *
     * @param limit The number of records to fetch.
     * @param offset The number of records to skip.
     * @return A [Result] indicating success or a [NetworkError].
     */
    override suspend fun syncAstronauts(limit: Int, offset: Int): Result<Unit, NetworkError> {
        val result = remoteDataSource.getAstronauts(limit, offset)
        return when (result) {
            is Result.Error -> Result.Error(result.error)
            is Result.Success -> {
                val entities = result.data.results.map { it.toEntity() }
                if (offset == 0) {
                    astronautDao.clearAll()
                }
                astronautDao.insertAstronauts(entities)
                Result.Success(Unit)
            }
        }
    }
}
