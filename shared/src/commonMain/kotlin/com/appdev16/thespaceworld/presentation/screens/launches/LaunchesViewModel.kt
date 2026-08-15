package com.appdev16.thespaceworld.presentation.screens.launches

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appdev16.thespaceworld.domain.modal.launches.Launch
import com.appdev16.thespaceworld.domain.usecase.GetLaunchesUseCase
import com.appdev16.thespaceworld.util.NetworkError
import com.appdev16.thespaceworld.util.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
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
        loadLaunches()
    }

    fun onAction(action: LaunchesAction) {
        when (action) {
            LaunchesAction.Refresh -> {
                _state.update { it.copy(currentOffset = 0, endReached = false, launches = emptyList()) }
                loadLaunches()
            }
            LaunchesAction.LoadNextPage -> {
                loadLaunches(isNextPage = true)
            }
            LaunchesAction.Retry -> {
                loadLaunches(isNextPage = _state.value.currentOffset > 0)
            }
        }
    }

    private fun loadLaunches(isNextPage: Boolean = false) {
        if (_state.value.isLoading || _state.value.isPaginationLoading || _state.value.endReached) return

        viewModelScope.launch {
            if (isNextPage) {
                _state.update { it.copy(isPaginationLoading = true, error = null) }
            } else {
                _state.update { it.copy(isLoading = true, error = null) }
            }
            
            val result = getLaunchesUseCase.execute(
                limit = pageSize,
                offset = _state.value.currentOffset
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
                    val newLaunches = result.data
                    _state.update { 
                        it.copy(
                            isLoading = false,
                            isPaginationLoading = false,
                            launches = it.launches + newLaunches,
                            currentOffset = it.currentOffset + newLaunches.size,
                            endReached = newLaunches.size < pageSize
                        )
                    }
                }
            }
        }
    }
}
