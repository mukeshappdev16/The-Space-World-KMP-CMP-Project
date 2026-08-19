package com.appdev16.thespaceworld.presentation.screens.agencies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appdev16.thespaceworld.domain.modal.agencies.Agency
import com.appdev16.thespaceworld.domain.usecase.GetAgenciesUseCase
import com.appdev16.thespaceworld.util.NetworkError
import com.appdev16.thespaceworld.util.Result
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

sealed interface AgenciesAction {
    data object Refresh : AgenciesAction
    data object LoadNextPage : AgenciesAction
    data object Retry : AgenciesAction
}

data class AgenciesUiState(
    val isLoading: Boolean = false,
    val isPaginationLoading: Boolean = false,
    val agencies: List<Agency> = emptyList(),
    val error: NetworkError? = null,
    val endReached: Boolean = false,
    val currentOffset: Int = 0
)

class AgenciesViewModel(
    private val getAgenciesUseCase: GetAgenciesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(AgenciesUiState())
    val state = _state.asStateFlow()

    private val pageSize = 20

    init {
        observeAgencies()
    }

    private fun observeAgencies() {
        getAgenciesUseCase.getAgencies()
            .onEach { agencies ->
                _state.update { it.copy(
                    agencies = agencies,
                    currentOffset = agencies.size
                ) }
                
                if (agencies.isEmpty() && !_state.value.isLoading) {
                    sync(isNextPage = false)
                }
            }
            .launchIn(viewModelScope)
    }

    fun onAction(action: AgenciesAction) {
        when (action) {
            AgenciesAction.Refresh -> sync(isNextPage = false)
            AgenciesAction.LoadNextPage -> sync(isNextPage = true)
            AgenciesAction.Retry -> sync(isNextPage = _state.value.currentOffset > 0)
        }
    }

    private fun sync(isNextPage: Boolean) {
        if (_state.value.isLoading || _state.value.isPaginationLoading || _state.value.endReached) return

        viewModelScope.launch {
            if (isNextPage) {
                _state.update { it.copy(isPaginationLoading = true, error = null) }
            } else {
                _state.update { it.copy(isLoading = true, error = null) }
            }
            
            val offset = if (isNextPage) _state.value.currentOffset else 0
            val result = getAgenciesUseCase.sync(
                limit = pageSize,
                offset = offset
            )

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
                            isPaginationLoading = false,
                        )
                    }
                }
            }
        }
    }
}
