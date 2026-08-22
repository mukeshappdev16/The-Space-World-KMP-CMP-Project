package com.appdev16.thespaceworld.domain.repositories

import com.appdev16.thespaceworld.domain.modal.agencies.Agency
import com.appdev16.thespaceworld.util.NetworkError
import com.appdev16.thespaceworld.util.Result
import kotlinx.coroutines.flow.Flow

/**
 * Repository for managing space agencies data.
 */
interface AgenciesRepository {
    /**
     * Returns a stream of all agencies.
     */
    fun getAgencies(): Flow<List<Agency>>

    /**
     * Returns a stream containing the result of fetching an agency by [id].
     */
    fun getAgencyById(id: Int): Flow<Result<Agency, NetworkError>>

    /**
     * Synchronizes local agency data with remote source.
     */
    suspend fun syncAgencies(limit: Int, offset: Int): Result<Unit, NetworkError>
}
