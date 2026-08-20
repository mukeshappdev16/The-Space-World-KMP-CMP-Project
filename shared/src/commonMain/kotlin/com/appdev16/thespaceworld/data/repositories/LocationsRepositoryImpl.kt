package com.appdev16.thespaceworld.data.repositories

import com.appdev16.thespaceworld.data.database.dao.LocationDao
import com.appdev16.thespaceworld.data.mappers.toDomain
import com.appdev16.thespaceworld.data.mappers.toEntity
import com.appdev16.thespaceworld.data.remote.LocationsRemoteDataSource
import com.appdev16.thespaceworld.domain.modal.locations.Location
import com.appdev16.thespaceworld.domain.repositories.LocationsRepository
import com.appdev16.thespaceworld.util.NetworkError
import com.appdev16.thespaceworld.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocationsRepositoryImpl(
    private val remoteDataSource: LocationsRemoteDataSource,
    private val locationDao: LocationDao
) : LocationsRepository {

    override fun getLocations(): Flow<List<Location>> {
        return locationDao.getLocations().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun getLocationById(id: Int): Flow<Location?> {
        return locationDao.getLocationById(id).map { it?.toDomain() }
    }

    override suspend fun syncLocations(limit: Int, offset: Int): Result<Unit, NetworkError> {
        val result = remoteDataSource.getLocations(limit, offset)
        return when (result) {
            is Result.Error -> Result.Error(result.error)
            is Result.Success -> {
                val entities = result.data.results.map { it.toEntity() }
                if (offset == 0) {
                    locationDao.clearAll()
                }
                locationDao.insertLocations(entities)
                Result.Success(Unit)
            }
        }
    }
}
