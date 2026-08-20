package com.appdev16.thespaceworld.presentation.screens.locations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appdev16.thespaceworld.domain.modal.locations.Location
import com.appdev16.thespaceworld.domain.usecase.GetLocationsUseCase
import com.appdev16.thespaceworld.util.NetworkError
import com.appdev16.thespaceworld.util.Result
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

sealed interface LocationsAction {
    data object Refresh : LocationsAction
    data object LoadNextPage : LocationsAction
    data object Retry : LocationsAction
}

data class LocationsUiState(
    val isLoading: Boolean = false,
    val isPaginationLoading: Boolean = false,
    val locations: List<Location> = emptyList(),
    val error: NetworkError? = null,
    val endReached: Boolean = false,
    val currentOffset: Int = 0
)

class LocationsViewModel(
    private val getLocationsUseCase: GetLocationsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(LocationsUiState())
    val state = _state.asStateFlow()

    private val pageSize = 20

    init {
        observeLocations()
    }

    private fun observeLocations() {
        getLocationsUseCase.getLocations()
            .onEach { locations ->
                _state.update { it.copy(
                    locations = locations,
                    currentOffset = locations.size
                ) }
                
                if (locations.isEmpty() && !_state.value.isLoading) {
                    sync(isNextPage = false)
                }
            }
            .launchIn(viewModelScope)
    }

    fun onAction(action: LocationsAction) {
        when (action) {
            LocationsAction.Refresh -> sync(isNextPage = false)
            LocationsAction.LoadNextPage -> sync(isNextPage = true)
            LocationsAction.Retry -> sync(isNextPage = _state.value.currentOffset > 0)
        }
    }

    private fun sync(isNextPage: Boolean) {
        if (_state.value.isLoading || _state.value.isPaginationLoading || (isNextPage && _state.value.endReached)) return

        viewModelScope.launch {
            if (isNextPage) {
                _state.update { it.copy(isPaginationLoading = true, error = null) }
            } else {
                _state.update { it.copy(isLoading = true, error = null) }
            }

            val offset = if (isNextPage) _state.value.currentOffset else 0
            val result = getLocationsUseCase.sync(limit = pageSize, offset = offset)

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
                            isPaginationLoading = false
                        )
                    }
                }
            }
        }
    }
}
