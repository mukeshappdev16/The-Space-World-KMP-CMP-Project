package com.appdev16.thespaceworld.presentation.screens.launches

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appdev16.thespaceworld.domain.modal.launches.Launch
import com.appdev16.thespaceworld.domain.usecase.GetLaunchesUseCase
import com.appdev16.thespaceworld.util.NetworkError
import com.appdev16.thespaceworld.util.Result
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

sealed interface LaunchesAction {
    data object Refresh : LaunchesAction
    data object LoadNextPage : LaunchesAction
    data object Retry : LaunchesAction
}

data class LaunchesUiState(
    val isLoading: Boolean = false,
    val isPaginationLoading: Boolean = false,
    val launches: List<Launch> = emptyList(),
    val error: NetworkError? = null,
    val endReached: Boolean = false,
    val currentOffset: Int = 0
)

class LaunchesViewModel(
    private val getLaunchesUseCase: GetLaunchesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(LaunchesUiState())
    val state = _state.asStateFlow()

    private val pageSize = 20

    init {
        observeLaunches()
    }

    private fun observeLaunches() {
        getLaunchesUseCase.getLaunches()
            .onEach { launches ->
                _state.update { it.copy(
                    launches = launches,
                    currentOffset = launches.size
                ) }
                
                // Trigger initial sync if DB is empty
                if (launches.isEmpty() && !_state.value.isLoading) {
                    sync(isNextPage = false)
                }
            }
            .launchIn(viewModelScope)
    }

    fun onAction(action: LaunchesAction) {
        when (action) {
            LaunchesAction.Refresh -> {
                sync(isNextPage = false)
            }
            LaunchesAction.LoadNextPage -> {
                sync(isNextPage = true)
            }
            LaunchesAction.Retry -> {
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
            val result = getLaunchesUseCase.sync(
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
                            // endReached logic: if we got less than requested, we reached the end
                            // However, we don't know the total from Result<Unit>, 
                            // so we rely on the repository to have inserted them.
                        )
                    }
                }
            }
        }
    }
}
