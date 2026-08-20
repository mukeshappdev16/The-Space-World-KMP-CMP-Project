package com.appdev16.thespaceworld.data.repositories

import com.appdev16.thespaceworld.data.database.dao.AstronautDao
import com.appdev16.thespaceworld.data.mappers.toDomain
import com.appdev16.thespaceworld.data.mappers.toEntity
import com.appdev16.thespaceworld.data.remote.AstronautsRemoteDataSource
import com.appdev16.thespaceworld.domain.modal.astronauts.Astronaut
import com.appdev16.thespaceworld.domain.repositories.AstronautsRepository
import com.appdev16.thespaceworld.util.NetworkError
import com.appdev16.thespaceworld.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AstronautsRepositoryImpl(
    private val remoteDataSource: AstronautsRemoteDataSource,
    private val astronautDao: AstronautDao
) : AstronautsRepository {

    override fun getAstronauts(): Flow<List<Astronaut>> {
        return astronautDao.getAstronauts().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun getAstronautById(id: Int): Flow<Astronaut?> {
        return astronautDao.getAstronautById(id).map { it?.toDomain() }
    }

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
