package com.appdev16.thespaceworld.domain.repositories

import com.appdev16.thespaceworld.domain.modal.astronauts.Astronaut
import com.appdev16.thespaceworld.util.NetworkError
import com.appdev16.thespaceworld.util.Result
import kotlinx.coroutines.flow.Flow

/**
 * Repository for managing astronauts data.
 */
interface AstronautsRepository {
    /**
     * Returns a stream of all astronauts.
     */
    fun getAstronauts(): Flow<List<Astronaut>>

    /**
     * Returns a stream of a single astronaut by [id].
     */
    fun getAstronautById(id: Int): Flow<Astronaut?>

    /**
     * Synchronizes local astronaut data with remote source.
     */
    suspend fun syncAstronauts(limit: Int, offset: Int): Result<Unit, NetworkError>
}
