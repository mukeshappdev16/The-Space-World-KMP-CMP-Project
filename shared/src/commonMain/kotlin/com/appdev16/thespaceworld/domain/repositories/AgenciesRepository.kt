package com.appdev16.thespaceworld.domain.repositories

import com.appdev16.thespaceworld.domain.modal.agencies.Agency
import com.appdev16.thespaceworld.util.NetworkError
import com.appdev16.thespaceworld.util.Result
import kotlinx.coroutines.flow.Flow

interface AgenciesRepository {
    fun getAgencies(): Flow<List<Agency>>
    fun getAgencyById(id: Int): Flow<Result<Agency, NetworkError>>
    suspend fun syncAgencies(limit: Int, offset: Int): Result<Unit, NetworkError>
}
