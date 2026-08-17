package com.appdev16.thespaceworld.domain.usecase

import com.appdev16.thespaceworld.domain.repositories.LaunchesRepository

class GetLaunchDetailUseCase(
    private val repository: LaunchesRepository
) {
    fun execute(id: String) = repository.getLaunchById(id)
}
