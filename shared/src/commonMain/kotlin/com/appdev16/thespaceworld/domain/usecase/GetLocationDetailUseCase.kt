package com.appdev16.thespaceworld.domain.usecase

import com.appdev16.thespaceworld.domain.modal.locations.Location
import com.appdev16.thespaceworld.domain.repositories.LocationsRepository
import kotlinx.coroutines.flow.Flow

class GetLocationDetailUseCase(
    private val repository: LocationsRepository
) {
    fun execute(id: Int): Flow<Location?> {
        return repository.getLocationById(id)
    }
}
