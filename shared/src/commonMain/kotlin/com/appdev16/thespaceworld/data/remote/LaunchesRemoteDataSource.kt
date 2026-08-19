package com.appdev16.thespaceworld.data.remote

import com.appdev16.thespaceworld.data.dto.launches.LaunchResponseDto
import com.appdev16.thespaceworld.util.NetworkError
import com.appdev16.thespaceworld.util.Result
import com.appdev16.thespaceworld.util.safeCall
import io.ktor.client.HttpClient
import io.ktor.client.request.get

interface LaunchesRemoteDataSource {
    suspend fun getLaunches(limit: Int, offset: Int): Result<LaunchResponseDto, NetworkError>
}

class LaunchesRemoteDataSourceImpl(
    private val httpClient: HttpClient
) : LaunchesRemoteDataSource {
    override suspend fun getLaunches(
        limit: Int,
        offset: Int
    ): Result<LaunchResponseDto, NetworkError> {
        return safeCall {
            httpClient.get("launches/?format=json&&ordering=-last_updated&limit=$limit&offset=$offset")
        }
    }
}
