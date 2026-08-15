package com.appdev16.thespaceworld.domain.usecase

import com.appdev16.thespaceworld.domain.modal.launches.Launch
import com.appdev16.thespaceworld.domain.repositories.LaunchesRepository
import com.appdev16.thespaceworld.util.NetworkError
import com.appdev16.thespaceworld.util.Result

class GetLaunchesUseCase(
    private val repository: LaunchesRepository
) {
    suspend fun execute(limit: Int = 20, offset: Int = 0): Result<List<Launch>, NetworkError> {
        return repository.getSpaceLaunches(limit, offset)
    }
}
