package com.appdev16.thespaceworld.data.repositories

import com.appdev16.thespaceworld.data.database.dao.EventDao
import com.appdev16.thespaceworld.data.mappers.toDomain
import com.appdev16.thespaceworld.data.mappers.toEntity
import com.appdev16.thespaceworld.data.remote.EventsRemoteDataSource
import com.appdev16.thespaceworld.domain.modal.events.Event
import com.appdev16.thespaceworld.domain.repositories.EventsRepository
import com.appdev16.thespaceworld.util.NetworkError
import com.appdev16.thespaceworld.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class EventsRepositoryImpl(
    private val remoteDataSource: EventsRemoteDataSource,
    private val eventDao: EventDao
) : EventsRepository {

    override fun getEvents(): Flow<List<Event>> {
        return eventDao.getEvents().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun getEventById(id: Int): Flow<Event?> {
        return eventDao.getEventById(id).map { it?.toDomain() }
    }

    override suspend fun syncEvents(limit: Int, offset: Int): Result<Unit, NetworkError> {
        val result = remoteDataSource.getEvents(limit, offset)
        return when (result) {
            is Result.Error -> Result.Error(result.error)
            is Result.Success -> {
                val entities = result.data.results.map { it.toEntity() }
                if (offset == 0) {
                    eventDao.clearAll()
                }
                eventDao.insertEvents(entities)
                Result.Success(Unit)
            }
        }
    }
}
