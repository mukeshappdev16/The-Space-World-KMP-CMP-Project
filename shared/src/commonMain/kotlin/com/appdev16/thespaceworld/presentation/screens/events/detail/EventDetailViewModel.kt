package com.appdev16.thespaceworld.presentation.screens.events.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appdev16.thespaceworld.domain.modal.events.Event
import com.appdev16.thespaceworld.domain.usecase.GetEventDetailUseCase
import kotlinx.coroutines.flow.*

data class EventDetailUiState(
    val isLoading: Boolean = true,
    val event: Event? = null
)

class EventDetailViewModel(
    private val getEventDetailUseCase: GetEventDetailUseCase,
    private val eventId: Int
) : ViewModel() {

    private val _state = MutableStateFlow(EventDetailUiState())
    val state = _state.asStateFlow()

    init {
        loadEvent()
    }

    private fun loadEvent() {
        getEventDetailUseCase.execute(eventId)
            .onEach { event ->
                _state.update { it.copy(
                    isLoading = false,
                    event = event
                ) }
            }
            .launchIn(viewModelScope)
    }
}
