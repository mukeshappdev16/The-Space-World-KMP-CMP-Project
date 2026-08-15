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

data class LaunchesUiState(
    val isLoading: Boolean = false,
    val launches: List<Launch> = emptyList(),
    val error: NetworkError? = null
)

class LaunchesViewModel(
    private val getLaunchesUseCase: GetLaunchesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(LaunchesUiState())
    val state = _state.asStateFlow()

    init {
        loadLaunches()
    }

    private fun loadLaunches() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            
            when (val result = getLaunchesUseCase.execute()) {
                is Result.Error -> {
                    _state.update { it.copy(isLoading = false, error = result.error) }
                }
                is Result.Success -> {
                    _state.update { it.copy(isLoading = false, launches = result.data) }
                }
            }
        }
    }
}
