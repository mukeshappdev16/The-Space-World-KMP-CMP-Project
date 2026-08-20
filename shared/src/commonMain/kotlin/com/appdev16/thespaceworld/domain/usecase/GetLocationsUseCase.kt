package com.appdev16.thespaceworld.domain.usecase

import com.appdev16.thespaceworld.domain.modal.locations.Location
import com.appdev16.thespaceworld.domain.repositories.LocationsRepository
import com.appdev16.thespaceworld.util.NetworkError
import com.appdev16.thespaceworld.util.Result
import kotlinx.coroutines.flow.Flow

class GetLocationsUseCase(
    private val repository: LocationsRepository
) {
    fun getLocations(): Flow<List<Location>> {
        return repository.getLocations()
    }

    suspend fun sync(limit: Int = 20, offset: Int = 0): Result<Unit, NetworkError> {
        return repository.syncLocations(limit, offset)
    }
}
