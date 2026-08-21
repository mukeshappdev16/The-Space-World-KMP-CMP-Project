package com.appdev16.thespaceworld.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appdev16.thespaceworld.domain.modal.launches.Launch
import com.appdev16.thespaceworld.domain.usecase.GetLaunchesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class HomeUiState(
    val nextLaunch: Launch? = null,
    val isLoading: Boolean = false
)

class HomeViewModel(
    private val getLaunchesUseCase: GetLaunchesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(HomeUiState())
    val state = _state.asStateFlow()

    init {
        observeNextLaunch()
    }

    private fun observeNextLaunch() {
        _state.update { it.copy(isLoading = true) }
        getLaunchesUseCase.getLaunches()
            .onEach { launches ->
                val next = launches.firstOrNull()
                _state.update { it.copy(
                    nextLaunch = next,
                    isLoading = false
                ) }

                if (launches.isEmpty()) {
                    syncNextLaunch()
                }
            }
            .launchIn(viewModelScope)
    }

    private fun syncNextLaunch() {
        viewModelScope.launch {
            try {
                getLaunchesUseCase.sync(limit = 1)
            } catch (e: Exception) {
                // Ignore
            }
        }
    }
}
