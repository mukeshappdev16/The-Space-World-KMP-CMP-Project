package com.appdev16.thespaceworld.domain.usecase

import com.appdev16.thespaceworld.domain.modal.spacestations.SpaceStation
import com.appdev16.thespaceworld.domain.repositories.SpaceStationsRepository
import com.appdev16.thespaceworld.util.NetworkError
import com.appdev16.thespaceworld.util.Result
import kotlinx.coroutines.flow.Flow

class GetSpaceStationsUseCase(
    private val repository: SpaceStationsRepository
) {
    fun getSpaceStations(): Flow<List<SpaceStation>> {
        return repository.getSpaceStations()
    }

    suspend fun sync(limit: Int = 20, offset: Int = 0): Result<Unit, NetworkError> {
        return repository.syncSpaceStations(limit, offset)
    }
}
