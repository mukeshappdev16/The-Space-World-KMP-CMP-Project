package com.appdev16.thespaceworld.domain.usecase

import com.appdev16.thespaceworld.domain.repositories.EventsRepository

class GetEventDetailUseCase(
    private val repository: EventsRepository
) {
    fun execute(id: Int) = repository.getEventById(id)
}
