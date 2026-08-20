package com.appdev16.thespaceworld.presentation.screens.spacecrafts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appdev16.thespaceworld.domain.modal.spacecrafts.SpacecraftConfig
import com.appdev16.thespaceworld.domain.usecase.GetSpacecraftsUseCase
import com.appdev16.thespaceworld.util.NetworkError
import com.appdev16.thespaceworld.util.Result
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

sealed interface SpacecraftsAction {
    data object Refresh : SpacecraftsAction
    data object LoadNextPage : SpacecraftsAction
    data object Retry : SpacecraftsAction
}

data class SpacecraftsUiState(
    val isLoading: Boolean = false,
    val isPaginationLoading: Boolean = false,
    val spacecrafts: List<SpacecraftConfig> = emptyList(),
    val error: NetworkError? = null,
    val endReached: Boolean = false,
    val currentOffset: Int = 0
)

class SpacecraftsViewModel(
    private val getSpacecraftsUseCase: GetSpacecraftsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(SpacecraftsUiState())
    val state = _state.asStateFlow()

    private val pageSize = 20

    init {
        observeSpacecrafts()
    }

    private fun observeSpacecrafts() {
        getSpacecraftsUseCase.getSpacecrafts()
            .onEach { spacecrafts ->
                _state.update { it.copy(
                    spacecrafts = spacecrafts,
                    currentOffset = spacecrafts.size
                ) }
                
                if (spacecrafts.isEmpty() && !_state.value.isLoading) {
                    sync(isNextPage = false)
                }
            }
            .launchIn(viewModelScope)
    }

    fun onAction(action: SpacecraftsAction) {
        when (action) {
            SpacecraftsAction.Refresh -> sync(isNextPage = false)
            SpacecraftsAction.LoadNextPage -> sync(isNextPage = true)
            SpacecraftsAction.Retry -> sync(isNextPage = _state.value.currentOffset > 0)
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
            val result = getSpacecraftsUseCase.sync(limit = pageSize, offset = offset)

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
