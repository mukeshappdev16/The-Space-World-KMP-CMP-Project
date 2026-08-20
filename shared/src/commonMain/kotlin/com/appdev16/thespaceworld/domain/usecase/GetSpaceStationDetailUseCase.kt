package com.appdev16.thespaceworld.domain.usecase

import com.appdev16.thespaceworld.domain.modal.spacestations.SpaceStation
import com.appdev16.thespaceworld.domain.repositories.SpaceStationsRepository
import kotlinx.coroutines.flow.Flow

class GetSpaceStationDetailUseCase(
    private val repository: SpaceStationsRepository
) {
    fun execute(id: Int): Flow<SpaceStation?> {
        return repository.getSpaceStationDetail(id)
    }
}
