package com.appdev16.thespaceworld.domain.usecase

import com.appdev16.thespaceworld.domain.modal.spacecrafts.SpacecraftConfig
import com.appdev16.thespaceworld.domain.repositories.SpacecraftsRepository
import kotlinx.coroutines.flow.Flow

class GetSpacecraftDetailUseCase(
    private val repository: SpacecraftsRepository
) {
    fun execute(id: Int): Flow<SpacecraftConfig?> {
        return repository.getSpacecraftDetail(id)
    }
}
