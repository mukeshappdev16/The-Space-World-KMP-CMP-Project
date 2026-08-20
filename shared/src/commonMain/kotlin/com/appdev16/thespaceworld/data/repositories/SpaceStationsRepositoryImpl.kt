package com.appdev16.thespaceworld.data.repositories

import com.appdev16.thespaceworld.data.database.dao.SpaceStationDao
import com.appdev16.thespaceworld.data.mappers.toDomain
import com.appdev16.thespaceworld.data.mappers.toEntity
import com.appdev16.thespaceworld.data.remote.SpaceStationsRemoteDataSource
import com.appdev16.thespaceworld.domain.modal.spacestations.SpaceStation
import com.appdev16.thespaceworld.domain.repositories.SpaceStationsRepository
import com.appdev16.thespaceworld.util.NetworkError
import com.appdev16.thespaceworld.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SpaceStationsRepositoryImpl(
    private val remoteDataSource: SpaceStationsRemoteDataSource,
    private val spaceStationDao: SpaceStationDao
) : SpaceStationsRepository {

    override fun getSpaceStations(): Flow<List<SpaceStation>> {
        return spaceStationDao.getSpaceStations().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun getSpaceStationDetail(id: Int): Flow<SpaceStation?> {
        return spaceStationDao.getSpaceStationById(id).map { it?.toDomain() }
    }

    override suspend fun syncSpaceStations(limit: Int, offset: Int): Result<Unit, NetworkError> {
        val result = remoteDataSource.getSpaceStations(limit, offset)
        return when (result) {
            is Result.Error -> Result.Error(result.error)
            is Result.Success -> {
                val entities = result.data.results.map { it.toEntity() }
                if (offset == 0) {
                    spaceStationDao.clearAll()
                }
                spaceStationDao.insertSpaceStations(entities)
                Result.Success(Unit)
            }
        }
    }
}
