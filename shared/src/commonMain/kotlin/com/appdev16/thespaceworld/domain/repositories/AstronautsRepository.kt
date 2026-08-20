package com.appdev16.thespaceworld.domain.repositories

import com.appdev16.thespaceworld.domain.modal.astronauts.Astronaut
import com.appdev16.thespaceworld.util.NetworkError
import com.appdev16.thespaceworld.util.Result
import kotlinx.coroutines.flow.Flow

interface AstronautsRepository {
    fun getAstronauts(): Flow<List<Astronaut>>
    fun getAstronautById(id: Int): Flow<Astronaut?>
    suspend fun syncAstronauts(limit: Int, offset: Int): Result<Unit, NetworkError>
}
