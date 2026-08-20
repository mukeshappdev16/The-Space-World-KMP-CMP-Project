package com.appdev16.thespaceworld.domain.usecase

import com.appdev16.thespaceworld.domain.modal.spacestations.SpaceStation
import com.appdev16.thespaceworld.domain.repositories.SpaceStationsRepository
import com.appdev16.thespaceworld.util.NetworkError
import com.appdev16.thespaceworld.util.Result

class GetSpaceStationsUseCase(
    private val repository: SpaceStationsRepository
) {
    suspend operator fun invoke(limit: Int = 20, offset: Int = 0): Result<List<SpaceStation>, NetworkError> {
        return repository.getSpaceStations(limit, offset)
    }
}
