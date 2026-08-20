package com.appdev16.thespaceworld.presentation.screens.spacestations.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appdev16.thespaceworld.domain.modal.spacestations.SpaceStation
import com.appdev16.thespaceworld.domain.usecase.GetSpaceStationDetailUseCase
import com.appdev16.thespaceworld.util.NetworkError
import kotlinx.coroutines.flow.*

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

    private fun loadStation() {
        getSpaceStationDetailUseCase.execute(stationId)
            .onEach { station ->
                _state.update { it.copy(
                    isLoading = false,
                    station = station
                ) }
            }
            .launchIn(viewModelScope)
    }
}
