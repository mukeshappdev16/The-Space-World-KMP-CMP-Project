package com.appdev16.thespaceworld.data.remote

import com.appdev16.thespaceworld.data.dto.astronauts.AstronautResponseDto
import com.appdev16.thespaceworld.util.NetworkError
import com.appdev16.thespaceworld.util.Result
import com.appdev16.thespaceworld.util.safeCall
import io.ktor.client.HttpClient
import io.ktor.client.request.get

interface AstronautsRemoteDataSource {
    suspend fun getAstronauts(limit: Int, offset: Int): Result<AstronautResponseDto, NetworkError>
}

class AstronautsRemoteDataSourceImpl(
    private val httpClient: HttpClient
) : AstronautsRemoteDataSource {
    override suspend fun getAstronauts(
        limit: Int,
        offset: Int
    ): Result<AstronautResponseDto, NetworkError> {
        return safeCall {
            httpClient.get("astronauts/?format=json&limit=$limit&offset=$offset")
        }
    }
}
