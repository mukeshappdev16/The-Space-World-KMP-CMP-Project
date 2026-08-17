package com.appdev16.thespaceworld.domain.usecase

import com.appdev16.thespaceworld.domain.modal.launches.Launch
import com.appdev16.thespaceworld.domain.repositories.LaunchesRepository
import com.appdev16.thespaceworld.util.NetworkError
import com.appdev16.thespaceworld.util.Result

class GetLaunchesUseCase(
    private val repository: LaunchesRepository
) {
    fun getLaunches() = repository.getSpaceLaunches()

    suspend fun sync(limit: Int = 20, offset: Int = 0) = repository.syncLaunches(limit, offset)
}
