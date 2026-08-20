package com.appdev16.thespaceworld.data.repositories

import com.appdev16.thespaceworld.data.database.dao.SpacecraftDao
import com.appdev16.thespaceworld.data.mappers.toDomain
import com.appdev16.thespaceworld.data.mappers.toEntity
import com.appdev16.thespaceworld.data.remote.SpacecraftsRemoteDataSource
import com.appdev16.thespaceworld.domain.modal.spacecrafts.SpacecraftConfig
import com.appdev16.thespaceworld.domain.repositories.SpacecraftsRepository
import com.appdev16.thespaceworld.util.NetworkError
import com.appdev16.thespaceworld.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SpacecraftsRepositoryImpl(
    private val remoteDataSource: SpacecraftsRemoteDataSource,
    private val spacecraftDao: SpacecraftDao
) : SpacecraftsRepository {

    override fun getSpacecrafts(): Flow<List<SpacecraftConfig>> {
        return spacecraftDao.getSpacecrafts().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun getSpacecraftDetail(id: Int): Flow<SpacecraftConfig?> {
        return spacecraftDao.getSpacecraftById(id).map { it?.toDomain() }
    }

    override suspend fun syncSpacecrafts(limit: Int, offset: Int): Result<Unit, NetworkError> {
        val result = remoteDataSource.getSpacecrafts(limit, offset)
        return when (result) {
            is Result.Error -> Result.Error(result.error)
            is Result.Success -> {
                val entities = result.data.results.map { it.toEntity() }
                if (offset == 0) {
                    spacecraftDao.clearAll()
                }
                spacecraftDao.insertSpacecrafts(entities)
                Result.Success(Unit)
            }
        }
    }
}
