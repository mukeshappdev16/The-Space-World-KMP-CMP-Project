package com.appdev16.thespaceworld.domain.usecase

import com.appdev16.thespaceworld.domain.modal.spacecrafts.SpacecraftConfig
import com.appdev16.thespaceworld.domain.repositories.SpacecraftsRepository
import com.appdev16.thespaceworld.util.NetworkError
import com.appdev16.thespaceworld.util.Result
import kotlinx.coroutines.flow.Flow

class GetSpacecraftsUseCase(
    private val repository: SpacecraftsRepository
) {
    fun getSpacecrafts(): Flow<List<SpacecraftConfig>> {
        return repository.getSpacecrafts()
    }

    suspend fun sync(limit: Int = 20, offset: Int = 0): Result<Unit, NetworkError> {
        return repository.syncSpacecrafts(limit, offset)
    }
}
