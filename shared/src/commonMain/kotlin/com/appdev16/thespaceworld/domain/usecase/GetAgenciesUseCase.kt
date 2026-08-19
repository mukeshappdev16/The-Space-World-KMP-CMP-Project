package com.appdev16.thespaceworld.domain.usecase

import com.appdev16.thespaceworld.domain.repositories.AgenciesRepository

class GetAgenciesUseCase(
    private val repository: AgenciesRepository
) {
    fun getAgencies() = repository.getAgencies()

    suspend fun sync(limit: Int = 20, offset: Int = 0) = repository.syncAgencies(limit, offset)
}
