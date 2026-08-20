package com.appdev16.thespaceworld.domain.usecase

import com.appdev16.thespaceworld.domain.repositories.AstronautsRepository

class GetAstronautsUseCase(
    private val repository: AstronautsRepository
) {
    fun getAstronauts() = repository.getAstronauts()

    suspend fun sync(limit: Int = 20, offset: Int = 0) = repository.syncAstronauts(limit, offset)
}
