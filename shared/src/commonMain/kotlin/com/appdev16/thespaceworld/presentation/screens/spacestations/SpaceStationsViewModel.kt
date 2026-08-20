package com.appdev16.thespaceworld.presentation.screens.spacestations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appdev16.thespaceworld.domain.modal.spacestations.SpaceStation
import com.appdev16.thespaceworld.domain.usecase.GetSpaceStationsUseCase
import com.appdev16.thespaceworld.util.NetworkError
import com.appdev16.thespaceworld.util.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

sealed interface SpaceStationsAction {
    data object Refresh : SpaceStationsAction
    data object LoadNextPage : SpaceStationsAction
    data object Retry : SpaceStationsAction
}

data class SpaceStationsUiState(
    val isLoading: Boolean = false,
    val isPaginationLoading: Boolean = false,
    val stations: List<SpaceStation> = emptyList(),
    val error: NetworkError? = null,
    val endReached: Boolean = false,
    val currentOffset: Int = 0
)

class SpaceStationsViewModel(
    private val getSpaceStationsUseCase: GetSpaceStationsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(SpaceStationsUiState())
    val state = _state.asStateFlow()

    private val pageSize = 20

    init {
        loadStations(isNextPage = false)
    }

    fun onAction(action: SpaceStationsAction) {
        when (action) {
            SpaceStationsAction.Refresh -> loadStations(isNextPage = false)
            SpaceStationsAction.LoadNextPage -> loadStations(isNextPage = true)
            SpaceStationsAction.Retry -> loadStations(isNextPage = _state.value.currentOffset > 0)
        }
    }

    private fun loadStations(isNextPage: Boolean) {
        if (_state.value.isLoading || _state.value.isPaginationLoading || (isNextPage && _state.value.endReached)) return

        viewModelScope.launch {
            if (isNextPage) {
                _state.update { it.copy(isPaginationLoading = true, error = null) }
            } else {
                _state.update { it.copy(isLoading = true, error = null, currentOffset = 0, stations = emptyList()) }
            }

            val offset = if (isNextPage) _state.value.currentOffset else 0
            val result = getSpaceStationsUseCase(limit = pageSize, offset = offset)

            when (result) {
                is Result.Error -> {
                    _state.update { it.copy(
                        isLoading = false,
                        isPaginationLoading = false,
                        error = result.error
                    ) }
                }
                is Result.Success -> {
                    val newStations = result.data
                    _state.update { 
                        it.copy(
                            isLoading = false,
                            isPaginationLoading = false,
                            stations = if (isNextPage) it.stations + newStations else newStations,
                            currentOffset = (if (isNextPage) it.currentOffset else 0) + newStations.size,
                            endReached = newStations.size < pageSize
                        )
                    }
                }
            }
        }
    }
}
