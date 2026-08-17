package com.appdev16.thespaceworld.presentation.screens.launches.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appdev16.thespaceworld.domain.modal.launches.Launch
import com.appdev16.thespaceworld.domain.usecase.GetLaunchDetailUseCase
import kotlinx.coroutines.flow.*

data class LaunchDetailUiState(
    val isLoading: Boolean = true,
    val launch: Launch? = null
)

class LaunchDetailViewModel(
    private val getLaunchDetailUseCase: GetLaunchDetailUseCase,
    private val launchId: String
) : ViewModel() {

    private val _state = MutableStateFlow(LaunchDetailUiState())
    val state = _state.asStateFlow()

    init {
        loadLaunch()
    }

    private fun loadLaunch() {
        getLaunchDetailUseCase.execute(launchId)
            .onEach { launch ->
                _state.update { it.copy(
                    isLoading = false,
                    launch = launch
                ) }
            }
            .launchIn(viewModelScope)
    }
}
