package com.appdev16.thespaceworld.domain.repositories

import com.appdev16.thespaceworld.domain.modal.events.Event
import com.appdev16.thespaceworld.util.NetworkError
import com.appdev16.thespaceworld.util.Result
import kotlinx.coroutines.flow.Flow

interface EventsRepository {
    fun getEvents(): Flow<List<Event>>
    fun getEventById(id: Int): Flow<Event?>
    suspend fun syncEvents(limit: Int, offset: Int): Result<Unit, NetworkError>
}
