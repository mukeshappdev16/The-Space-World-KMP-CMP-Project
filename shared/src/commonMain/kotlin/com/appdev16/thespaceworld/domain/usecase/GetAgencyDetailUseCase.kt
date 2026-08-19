package com.appdev16.thespaceworld.domain.usecase

import com.appdev16.thespaceworld.domain.repositories.AgenciesRepository

class GetAgencyDetailUseCase(
    private val repository: AgenciesRepository
) {
    fun execute(id: Int) = repository.getAgencyById(id)
}
