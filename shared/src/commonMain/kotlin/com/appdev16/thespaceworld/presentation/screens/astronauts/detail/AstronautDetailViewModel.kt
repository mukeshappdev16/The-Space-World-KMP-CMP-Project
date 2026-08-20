package com.appdev16.thespaceworld.presentation.screens.astronauts.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appdev16.thespaceworld.domain.modal.astronauts.Astronaut
import com.appdev16.thespaceworld.domain.usecase.GetAstronautDetailUseCase
import kotlinx.coroutines.flow.*

data class AstronautDetailUiState(
    val isLoading: Boolean = true,
    val astronaut: Astronaut? = null
)

class AstronautDetailViewModel(
    private val getAstronautDetailUseCase: GetAstronautDetailUseCase,
    private val astronautId: Int
) : ViewModel() {

    private val _state = MutableStateFlow(AstronautDetailUiState())
    val state = _state.asStateFlow()

    init {
        fetchAstronautDetail()
    }

    private fun fetchAstronautDetail() {
        getAstronautDetailUseCase(astronautId)
            .onEach { astronaut ->
                _state.update { it.copy(
                    isLoading = false,
                    astronaut = astronaut
                ) }
            }
            .launchIn(viewModelScope)
    }
}
