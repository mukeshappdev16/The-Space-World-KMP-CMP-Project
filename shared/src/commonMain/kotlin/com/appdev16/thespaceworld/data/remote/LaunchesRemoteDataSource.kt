package com.appdev16.thespaceworld.data.remote

import com.appdev16.thespaceworld.data.dto.launches.LaunchResponseDto
import com.appdev16.thespaceworld.util.NetworkError
import com.appdev16.thespaceworld.util.Result
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.serialization.SerializationException

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
        val response = try {
            httpClient.get("launches/?format=json&&ordering=-last_updated&limit=$limit&offset=$offset")
        } catch (e: UnresolvedAddressException) {
            return Result.Error(NetworkError.Network.NO_INTERNET)
        } catch (e: SerializationException) {
            return Result.Error(NetworkError.Network.SERIALIZATION)
        } catch (e: Exception) {
            return Result.Error(NetworkError.Network.UNKNOWN)
        }

        return when (response.status.value) {
            in 200..299 -> {
                try {
                    Result.Success(response.body<LaunchResponseDto>())
                } catch (e: SerializationException) {
                    Result.Error(NetworkError.Network.SERIALIZATION)
                }
            }

            401 -> Result.Error(NetworkError.Network.UNAUTHORIZED)
            409 -> Result.Error(NetworkError.Network.CONFLICT)
            408 -> Result.Error(NetworkError.Network.REQUEST_TIMEOUT)
            413 -> Result.Error(NetworkError.Network.PAYLOAD_TOO_LARGE)
            429 -> Result.Error(NetworkError.Network.TOO_MANY_REQUESTS)
            in 500..599 -> Result.Error(NetworkError.Network.SERVER_ERROR)
            else -> Result.Error(NetworkError.Network.UNKNOWN)
        }
    }
}
