package com.appdev16.thespaceworld.domain.repositories

import com.appdev16.thespaceworld.domain.modal.launches.Launch
import com.appdev16.thespaceworld.util.NetworkError
import com.appdev16.thespaceworld.util.Result
import kotlinx.coroutines.flow.Flow

interface LaunchesRepository {
    fun getSpaceLaunches(): Flow<List<Launch>>
    fun getLaunchById(id: String): Flow<Launch?>
    suspend fun syncLaunches(limit: Int, offset: Int): Result<Unit, NetworkError>
}
