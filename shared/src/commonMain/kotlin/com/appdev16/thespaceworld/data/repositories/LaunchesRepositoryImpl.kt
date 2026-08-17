package com.appdev16.thespaceworld.data.repositories

import com.appdev16.thespaceworld.data.database.dao.LaunchDao
import com.appdev16.thespaceworld.data.mappers.toDomain
import com.appdev16.thespaceworld.data.mappers.toEntity
import com.appdev16.thespaceworld.data.remote.LaunchesRemoteDataSource
import com.appdev16.thespaceworld.domain.modal.launches.Launch
import com.appdev16.thespaceworld.domain.repositories.LaunchesRepository
import com.appdev16.thespaceworld.util.NetworkError
import com.appdev16.thespaceworld.util.Result
import com.appdev16.thespaceworld.util.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LaunchesRepositoryImpl(
    private val remoteDataSource: LaunchesRemoteDataSource,
    private val launchDao: LaunchDao
) : LaunchesRepository {

    override fun getSpaceLaunches(): Flow<List<Launch>> {
        return launchDao.getLaunches().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun syncLaunches(limit: Int, offset: Int): Result<Unit, NetworkError> {
        val result = remoteDataSource.getLaunches(limit, offset)
        return when (result) {
            is Result.Error -> Result.Error(result.error)
            is Result.Success -> {
                val entities = result.data.results.map { it.toEntity() }
                if (offset == 0) {
                    launchDao.clearAll()
                }
                launchDao.insertLaunches(entities)
                Result.Success(Unit)
            }
        }
    }
}
