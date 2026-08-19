package com.appdev16.thespaceworld.data.remote

import com.appdev16.thespaceworld.data.dto.agencies.AgencyDto
import com.appdev16.thespaceworld.data.dto.agencies.AgencyResponseDto
import com.appdev16.thespaceworld.util.NetworkError
import com.appdev16.thespaceworld.util.Result
import com.appdev16.thespaceworld.util.safeCall
import io.ktor.client.HttpClient
import io.ktor.client.request.get

interface AgenciesRemoteDataSource {
    suspend fun getAgencies(limit: Int, offset: Int): Result<AgencyResponseDto, NetworkError>
    suspend fun getAgencyById(id: Int): Result<AgencyDto, NetworkError>
}

class AgenciesRemoteDataSourceImpl(
    private val httpClient: HttpClient
) : AgenciesRemoteDataSource {
    override suspend fun getAgencies(
        limit: Int,
        offset: Int
    ): Result<AgencyResponseDto, NetworkError> {
        return safeCall {
            httpClient.get("agencies/?format=json&limit=$limit&offset=$offset")
        }
    }

    override suspend fun getAgencyById(id: Int): Result<AgencyDto, NetworkError> {
        return safeCall {
            httpClient.get("agencies/$id/?format=json")
        }
    }
}
