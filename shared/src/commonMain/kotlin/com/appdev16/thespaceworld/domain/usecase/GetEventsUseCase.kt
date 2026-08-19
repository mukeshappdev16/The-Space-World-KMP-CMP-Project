package com.appdev16.thespaceworld.domain.usecase

import com.appdev16.thespaceworld.domain.repositories.EventsRepository
import com.appdev16.thespaceworld.util.NetworkError
import com.appdev16.thespaceworld.util.Result

class GetEventsUseCase(
    private val repository: EventsRepository
) {
    fun getEvents() = repository.getEvents()

    suspend fun sync(limit: Int = 20, offset: Int = 0) = repository.syncEvents(limit, offset)
}
