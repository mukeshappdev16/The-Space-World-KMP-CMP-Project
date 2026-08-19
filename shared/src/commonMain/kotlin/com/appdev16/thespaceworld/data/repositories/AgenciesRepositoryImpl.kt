package com.appdev16.thespaceworld.data.repositories

import com.appdev16.thespaceworld.data.database.dao.AgencyDao
import com.appdev16.thespaceworld.data.mappers.toDomain
import com.appdev16.thespaceworld.data.mappers.toEntity
import com.appdev16.thespaceworld.data.remote.AgenciesRemoteDataSource
import com.appdev16.thespaceworld.domain.modal.agencies.Agency
import com.appdev16.thespaceworld.domain.repositories.AgenciesRepository
import com.appdev16.thespaceworld.util.NetworkError
import com.appdev16.thespaceworld.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AgenciesRepositoryImpl(
    private val remoteDataSource: AgenciesRemoteDataSource,
    private val agencyDao: AgencyDao
) : AgenciesRepository {

    override fun getAgencies(): Flow<List<Agency>> {
        return agencyDao.getAgencies().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun getAgencyById(id: Int): Flow<Agency?> {
        return agencyDao.getAgencyById(id).map { it?.toDomain() }
    }

    override suspend fun syncAgencies(limit: Int, offset: Int): Result<Unit, NetworkError> {
        val result = remoteDataSource.getAgencies(limit, offset)
        return when (result) {
            is Result.Error -> Result.Error(result.error)
            is Result.Success -> {
                val entities = result.data.results.map { it.toEntity() }
                if (offset == 0) {
                    agencyDao.clearAll()
                }
                agencyDao.insertAgencies(entities)
                Result.Success(Unit)
            }
        }
    }
}
