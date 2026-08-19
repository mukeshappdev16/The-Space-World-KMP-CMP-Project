package com.appdev16.thespaceworld.presentation.screens.events

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appdev16.thespaceworld.domain.modal.events.Event
import com.appdev16.thespaceworld.domain.usecase.GetEventsUseCase
import com.appdev16.thespaceworld.util.NetworkError
import com.appdev16.thespaceworld.util.Result
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

sealed interface EventsAction {
    data object Refresh : EventsAction
    data object LoadNextPage : EventsAction
    data object Retry : EventsAction
}

data class EventsUiState(
    val isLoading: Boolean = false,
    val isPaginationLoading: Boolean = false,
    val events: List<Event> = emptyList(),
    val error: NetworkError? = null,
    val endReached: Boolean = false,
    val currentOffset: Int = 0
)

class EventsViewModel(
    private val getEventsUseCase: GetEventsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(EventsUiState())
    val state = _state.asStateFlow()

    private val pageSize = 20

    init {
        observeEvents()
    }

    private fun observeEvents() {
        getEventsUseCase.getEvents()
            .onEach { events ->
                _state.update { it.copy(
                    events = events,
                    currentOffset = events.size
                ) }
                
                if (events.isEmpty() && !_state.value.isLoading) {
                    sync(isNextPage = false)
                }
            }
            .launchIn(viewModelScope)
    }

    fun onAction(action: EventsAction) {
        when (action) {
            EventsAction.Refresh -> {
                sync(isNextPage = false)
            }
            EventsAction.LoadNextPage -> {
                sync(isNextPage = true)
            }
            EventsAction.Retry -> {
                sync(isNextPage = _state.value.currentOffset > 0)
            }
        }
    }

    private fun sync(isNextPage: Boolean) {
        if (_state.value.isLoading || _state.value.isPaginationLoading || _state.value.endReached) return

        viewModelScope.launch {
            if (isNextPage) {
                _state.update { it.copy(isPaginationLoading = true, error = null) }
            } else {
                _state.update { it.copy(isLoading = true, error = null) }
            }
            
            val offset = if (isNextPage) _state.value.currentOffset else 0
            val result = getEventsUseCase.sync(
                limit = pageSize,
                offset = offset
            )

            when (result) {
                is Result.Error -> {
                    _state.update { it.copy(
                        isLoading = false, 
                        isPaginationLoading = false,
                        error = result.error
                    ) }
                }
                is Result.Success -> {
                    _state.update { 
                        it.copy(
                            isLoading = false,
                            isPaginationLoading = false,
                        )
                    }
                }
            }
        }
    }
}
