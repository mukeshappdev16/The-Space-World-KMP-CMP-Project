package com.appdev16.thespaceworld.data.remote

import com.appdev16.thespaceworld.data.dto.spacecrafts.SpacecraftDto
import com.appdev16.thespaceworld.data.dto.spacecrafts.SpacecraftResponseDto
import com.appdev16.thespaceworld.util.NetworkError
import com.appdev16.thespaceworld.util.Result
import com.appdev16.thespaceworld.util.safeCall
import io.ktor.client.HttpClient
import io.ktor.client.request.get

interface SpacecraftsRemoteDataSource {
    suspend fun getSpacecrafts(limit: Int, offset: Int): Result<SpacecraftResponseDto, NetworkError>
    suspend fun getSpacecraftDetail(id: Int): Result<SpacecraftDto, NetworkError>
}

class SpacecraftsRemoteDataSourceImpl(
    private val httpClient: HttpClient
) : SpacecraftsRemoteDataSource {
    override suspend fun getSpacecrafts(
        limit: Int,
        offset: Int
    ): Result<SpacecraftResponseDto, NetworkError> {
        return safeCall {
            httpClient.get("spacecraft_configurations/?format=json&limit=$limit&offset=$offset")
        }
    }

    override suspend fun getSpacecraftDetail(id: Int): Result<SpacecraftDto, NetworkError> {
        return safeCall {
            httpClient.get("spacecraft_configurations/$id/?format=json")
        }
    }
}
