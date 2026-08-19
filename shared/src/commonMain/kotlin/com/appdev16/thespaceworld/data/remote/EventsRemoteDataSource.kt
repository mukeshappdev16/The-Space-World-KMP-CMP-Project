package com.appdev16.thespaceworld.data.remote

import com.appdev16.thespaceworld.data.dto.events.EventResponseDto
import com.appdev16.thespaceworld.util.NetworkError
import com.appdev16.thespaceworld.util.Result
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.serialization.SerializationException

interface EventsRemoteDataSource {
    suspend fun getEvents(limit: Int, offset: Int): Result<EventResponseDto, NetworkError>
}

class EventsRemoteDataSourceImpl(
    private val httpClient: HttpClient
) : EventsRemoteDataSource {
    override suspend fun getEvents(
        limit: Int,
        offset: Int
    ): Result<EventResponseDto, NetworkError> {
        val response = try {
            httpClient.get("events/?format=json&ordering=-last_updated&limit=$limit&offset=$offset")
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
                    Result.Success(response.body<EventResponseDto>())
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
