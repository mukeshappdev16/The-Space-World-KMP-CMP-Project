package com.appdev16.thespaceworld.presentation.screens.spacecrafts.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appdev16.thespaceworld.domain.modal.spacecrafts.SpacecraftConfig
import com.appdev16.thespaceworld.domain.usecase.GetSpacecraftDetailUseCase
import com.appdev16.thespaceworld.util.NetworkError
import kotlinx.coroutines.flow.*

data class SpacecraftDetailUiState(
    val isLoading: Boolean = true,
    val spacecraft: SpacecraftConfig? = null,
    val error: NetworkError? = null
)

class SpacecraftDetailViewModel(
    private val getSpacecraftDetailUseCase: GetSpacecraftDetailUseCase,
    private val spacecraftId: Int
) : ViewModel() {

    private val _state = MutableStateFlow(SpacecraftDetailUiState())
    val state = _state.asStateFlow()

    init {
        loadSpacecraft()
    }

    private fun loadSpacecraft() {
        getSpacecraftDetailUseCase.execute(spacecraftId)
            .onEach { spacecraft ->
                _state.update { it.copy(
                    isLoading = false,
                    spacecraft = spacecraft
                ) }
            }
            .launchIn(viewModelScope)
    }
}
