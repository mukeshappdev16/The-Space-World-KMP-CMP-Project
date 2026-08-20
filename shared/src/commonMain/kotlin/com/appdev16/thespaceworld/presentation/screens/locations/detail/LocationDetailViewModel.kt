package com.appdev16.thespaceworld.presentation.screens.locations.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appdev16.thespaceworld.domain.modal.locations.Location
import com.appdev16.thespaceworld.domain.usecase.GetLocationDetailUseCase
import com.appdev16.thespaceworld.util.NetworkError
import kotlinx.coroutines.flow.*

data class LocationDetailUiState(
    val isLoading: Boolean = true,
    val location: Location? = null,
    val error: NetworkError? = null
)

class LocationDetailViewModel(
    private val getLocationDetailUseCase: GetLocationDetailUseCase,
    private val locationId: Int
) : ViewModel() {

    private val _state = MutableStateFlow(LocationDetailUiState())
    val state = _state.asStateFlow()

    init {
        loadLocation()
    }

    private fun loadLocation() {
        getLocationDetailUseCase.execute(locationId)
            .onEach { location ->
                _state.update { it.copy(
                    isLoading = false,
                    location = location
                ) }
            }
            .launchIn(viewModelScope)
    }
}
