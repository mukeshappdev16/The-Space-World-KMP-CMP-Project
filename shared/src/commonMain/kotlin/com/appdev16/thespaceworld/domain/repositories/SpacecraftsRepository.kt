package com.appdev16.thespaceworld.domain.repositories

import com.appdev16.thespaceworld.domain.modal.spacecrafts.SpacecraftConfig
import com.appdev16.thespaceworld.util.NetworkError
import com.appdev16.thespaceworld.util.Result
import kotlinx.coroutines.flow.Flow

interface SpacecraftsRepository {
    fun getSpacecrafts(): Flow<List<SpacecraftConfig>>
    fun getSpacecraftDetail(id: Int): Flow<SpacecraftConfig?>
    suspend fun syncSpacecrafts(limit: Int, offset: Int): Result<Unit, NetworkError>
}
