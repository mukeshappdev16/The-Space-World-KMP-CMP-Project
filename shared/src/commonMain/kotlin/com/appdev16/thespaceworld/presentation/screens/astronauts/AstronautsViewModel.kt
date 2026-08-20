package com.appdev16.thespaceworld.presentation.screens.astronauts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appdev16.thespaceworld.domain.modal.astronauts.Astronaut
import com.appdev16.thespaceworld.domain.usecase.GetAstronautsUseCase
import com.appdev16.thespaceworld.util.NetworkError
import com.appdev16.thespaceworld.util.Result
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

sealed interface AstronautsAction {
    data object Refresh : AstronautsAction
    data object LoadNextPage : AstronautsAction
    data object Retry : AstronautsAction
}

data class AstronautsUiState(
    val isLoading: Boolean = false,
    val isPaginationLoading: Boolean = false,
    val astronauts: List<Astronaut> = emptyList(),
    val error: NetworkError? = null,
    val endReached: Boolean = false,
    val currentOffset: Int = 0
)

class AstronautsViewModel(
    private val getAstronautsUseCase: GetAstronautsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(AstronautsUiState())
    val state = _state.asStateFlow()

    private val pageSize = 20

    init {
        observeAstronauts()
    }

    private fun observeAstronauts() {
        getAstronautsUseCase.getAstronauts()
            .onEach { astronauts ->
                _state.update { it.copy(
                    astronauts = astronauts,
                    currentOffset = astronauts.size
                ) }
                
                if (astronauts.isEmpty() && !_state.value.isLoading) {
                    sync(isNextPage = false)
                }
            }
            .launchIn(viewModelScope)
    }

    fun onAction(action: AstronautsAction) {
        when (action) {
            AstronautsAction.Refresh -> sync(isNextPage = false)
            AstronautsAction.LoadNextPage -> sync(isNextPage = true)
            AstronautsAction.Retry -> sync(isNextPage = _state.value.currentOffset > 0)
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
            val result = getAstronautsUseCase.sync(
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
