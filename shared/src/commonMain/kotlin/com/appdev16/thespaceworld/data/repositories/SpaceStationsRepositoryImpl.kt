package com.appdev16.thespaceworld.data.repositories

import com.appdev16.thespaceworld.data.mappers.toDomain
import com.appdev16.thespaceworld.data.remote.SpaceStationsRemoteDataSource
import com.appdev16.thespaceworld.domain.modal.spacestations.SpaceStation
import com.appdev16.thespaceworld.domain.repositories.SpaceStationsRepository
import com.appdev16.thespaceworld.util.NetworkError
import com.appdev16.thespaceworld.util.Result
import com.appdev16.thespaceworld.util.map

class SpaceStationsRepositoryImpl(
    private val remoteDataSource: SpaceStationsRemoteDataSource
) : SpaceStationsRepository {
    override suspend fun getSpaceStations(
        limit: Int,
        offset: Int
    ): Result<List<SpaceStation>, NetworkError> {
        return remoteDataSource.getSpaceStations(limit, offset).map { response ->
            response.results.map { it.toDomain() }
        }
    }

    override suspend fun getSpaceStationDetail(id: Int): Result<SpaceStation, NetworkError> {
        return remoteDataSource.getSpaceStationDetail(id).map { it.toDomain() }
    }
}
