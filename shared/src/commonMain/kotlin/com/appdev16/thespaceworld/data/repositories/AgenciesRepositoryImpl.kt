package com.appdev16.thespaceworld.data.repositories

import com.appdev16.thespaceworld.data.database.dao.AgencyDao
import com.appdev16.thespaceworld.data.database.entities.AgencyEntity
import com.appdev16.thespaceworld.data.mappers.toDomain
import com.appdev16.thespaceworld.data.mappers.toEntity
import com.appdev16.thespaceworld.data.remote.AgenciesRemoteDataSource
import com.appdev16.thespaceworld.domain.modal.agencies.Agency
import com.appdev16.thespaceworld.domain.repositories.AgenciesRepository
import com.appdev16.thespaceworld.util.NetworkError
import com.appdev16.thespaceworld.util.Result
import com.appdev16.thespaceworld.util.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

/**
 * Implementation of [AgenciesRepository] that handles agency data operations.
 *
 * This repository manages the synchronization between the [AgenciesRemoteDataSource]
 * and the local [AgencyDao].
 *
 * @param remoteDataSource The source for fetching agency data from the network.
 * @param agencyDao The data access object for local agency persistence.
 */
class AgenciesRepositoryImpl(
    private val remoteDataSource: AgenciesRemoteDataSource,
    private val agencyDao: AgencyDao
) : AgenciesRepository {

    /**
     * Returns a stream of all agencies available in the local database.
     * The data is mapped from [AgencyEntity] to the [Agency] domain model.
     */
    override fun getAgencies(): Flow<List<Agency>> {
        return agencyDao.getAgencies().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    /**
     * Fetches a single agency by its [id] from the remote data source.
     */
    override fun getAgencyById(id: Int): Flow<Result<Agency, NetworkError>> = flow {
        emit(remoteDataSource.getAgencyById(id).map { it.toDomain() })
    }

    /**
     * Synchronizes the local database with agency data from the remote source.
     *
     * Fetches a list of agencies based on the provided [limit] and [offset].
     * If [offset] is 0, it clears existing local records.
     *
     * @param limit The number of records to fetch.
     * @param offset The number of records to skip.
     * @return A [Result] indicating success or a [NetworkError].
     */
    override suspend fun syncAgencies(limit: Int, offset: Int): Result<Unit, NetworkError> {
        val result = remoteDataSource.getAgencies(limit, offset)
        return when (result) {
            is Result.Error -> Result.Error(result.error)
            is Result.Success -> {
                val entities = result.data.results.map { it.toEntity() }
                if (offset == 0) {
                    agencyDao.clearAll()
                }
                agencyDao.insertAgencies(entities)
                Result.Success(Unit)
            }
        }
    }
}
