package com.appdev16.thespaceworld.domain.repositories

import com.appdev16.thespaceworld.domain.modal.locations.Location
import com.appdev16.thespaceworld.util.NetworkError
import com.appdev16.thespaceworld.util.Result
import kotlinx.coroutines.flow.Flow

interface LocationsRepository {
    fun getLocations(): Flow<List<Location>>
    fun getLocationById(id: Int): Flow<Location?>
    suspend fun syncLocations(limit: Int, offset: Int): Result<Unit, NetworkError>
}
