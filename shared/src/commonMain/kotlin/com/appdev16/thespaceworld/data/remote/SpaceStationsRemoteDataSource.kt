package com.appdev16.thespaceworld.data.remote

import com.appdev16.thespaceworld.data.dto.spacestations.SpaceStationDto
import com.appdev16.thespaceworld.data.dto.spacestations.SpaceStationResponseDto
import com.appdev16.thespaceworld.util.NetworkError
import com.appdev16.thespaceworld.util.Result
import com.appdev16.thespaceworld.util.safeCall
import io.ktor.client.HttpClient
import io.ktor.client.request.get

interface SpaceStationsRemoteDataSource {
    suspend fun getSpaceStations(limit: Int, offset: Int): Result<SpaceStationResponseDto, NetworkError>
    suspend fun getSpaceStationDetail(id: Int): Result<SpaceStationDto, NetworkError>
}

class SpaceStationsRemoteDataSourceImpl(
    private val httpClient: HttpClient
) : SpaceStationsRemoteDataSource {
    override suspend fun getSpaceStations(
        limit: Int,
        offset: Int
    ): Result<SpaceStationResponseDto, NetworkError> {
        return safeCall {
            httpClient.get("space_stations/?format=json&ordering=-last_updated&limit=$limit&offset=$offset")
        }
    }

    override suspend fun getSpaceStationDetail(id: Int): Result<SpaceStationDto, NetworkError> {
        return safeCall {
            httpClient.get("space_stations/$id/?format=json")
        }
    }
}
