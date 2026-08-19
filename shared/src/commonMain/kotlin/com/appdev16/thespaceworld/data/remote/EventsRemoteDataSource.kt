package com.appdev16.thespaceworld.data.remote

import com.appdev16.thespaceworld.data.dto.events.EventResponseDto
import com.appdev16.thespaceworld.util.NetworkError
import com.appdev16.thespaceworld.util.Result
import com.appdev16.thespaceworld.util.safeCall
import io.ktor.client.HttpClient
import io.ktor.client.request.get

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
        return safeCall {
            httpClient.get("events/?format=json&ordering=-last_updated&limit=$limit&offset=$offset")
        }
    }
}
