package com.appdev16.thespaceworld.domain.usecase

import com.appdev16.thespaceworld.domain.repositories.AstronautsRepository

class GetAstronautDetailUseCase(
    private val repository: AstronautsRepository
) {
    operator fun invoke(id: Int) = repository.getAstronautById(id)
}
