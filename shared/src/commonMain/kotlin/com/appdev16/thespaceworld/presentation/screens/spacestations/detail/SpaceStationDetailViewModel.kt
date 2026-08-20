package com.appdev16.thespaceworld.presentation.screens.spacestations.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appdev16.thespaceworld.domain.modal.spacestations.SpaceStation
import com.appdev16.thespaceworld.domain.usecase.GetSpaceStationDetailUseCase
import com.appdev16.thespaceworld.util.NetworkError
import com.appdev16.thespaceworld.util.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class SpaceStationDetailUiState(
    val isLoading: Boolean = true,
    val station: SpaceStation? = null,
    val error: NetworkError? = null
)

class SpaceStationDetailViewModel(
    private val getSpaceStationDetailUseCase: GetSpaceStationDetailUseCase,
    private val stationId: Int
) : ViewModel() {

    private val _state = MutableStateFlow(SpaceStationDetailUiState())
    val state = _state.asStateFlow()

    init {
        loadStation()
    }

    fun loadStation() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            val result = getSpaceStationDetailUseCase(stationId)
            when (result) {
                is Result.Error -> {
                    _state.update { it.copy(isLoading = false, error = result.error) }
                }
                is Result.Success -> {
                    _state.update { it.copy(isLoading = false, station = result.data) }
                }
            }
        }
    }
}
