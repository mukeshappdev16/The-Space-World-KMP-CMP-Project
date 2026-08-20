package com.appdev16.thespaceworld.domain.usecase

import com.appdev16.thespaceworld.domain.modal.spacestations.SpaceStation
import com.appdev16.thespaceworld.domain.repositories.SpaceStationsRepository
import com.appdev16.thespaceworld.util.NetworkError
import com.appdev16.thespaceworld.util.Result

class GetSpaceStationDetailUseCase(
    private val repository: SpaceStationsRepository
) {
    suspend operator fun invoke(id: Int): Result<SpaceStation, NetworkError> {
        return repository.getSpaceStationDetail(id)
    }
}
