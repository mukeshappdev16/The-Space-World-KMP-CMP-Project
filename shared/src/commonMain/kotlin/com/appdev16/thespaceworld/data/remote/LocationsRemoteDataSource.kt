package com.appdev16.thespaceworld.data.remote

import com.appdev16.thespaceworld.data.dto.locations.LocationDto
import com.appdev16.thespaceworld.data.dto.locations.LocationResponseDto
import com.appdev16.thespaceworld.util.NetworkError
import com.appdev16.thespaceworld.util.Result
import com.appdev16.thespaceworld.util.safeCall
import io.ktor.client.HttpClient
import io.ktor.client.request.get

interface LocationsRemoteDataSource {
    suspend fun getLocations(limit: Int, offset: Int): Result<LocationResponseDto, NetworkError>
    suspend fun getLocationDetail(id: Int): Result<LocationDto, NetworkError>
}

class LocationsRemoteDataSourceImpl(
    private val httpClient: HttpClient
) : LocationsRemoteDataSource {
    override suspend fun getLocations(
        limit: Int,
        offset: Int
    ): Result<LocationResponseDto, NetworkError> {
        return safeCall {
            httpClient.get("locations/?format=json&limit=$limit&offset=$offset")
        }
    }

    override suspend fun getLocationDetail(id: Int): Result<LocationDto, NetworkError> {
        return safeCall {
            httpClient.get("locations/$id/?format=json")
        }
    }
}
