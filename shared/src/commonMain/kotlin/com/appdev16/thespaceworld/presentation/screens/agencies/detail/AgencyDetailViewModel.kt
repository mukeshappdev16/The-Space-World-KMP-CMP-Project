package com.appdev16.thespaceworld.presentation.screens.agencies.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appdev16.thespaceworld.domain.modal.agencies.Agency
import com.appdev16.thespaceworld.domain.usecase.GetAgencyDetailUseCase
import kotlinx.coroutines.flow.*

data class AgencyDetailUiState(
    val isLoading: Boolean = true,
    val agency: Agency? = null
)

class AgencyDetailViewModel(
    private val getAgencyDetailUseCase: GetAgencyDetailUseCase,
    private val agencyId: Int
) : ViewModel() {

    private val _state = MutableStateFlow(AgencyDetailUiState())
    val state = _state.asStateFlow()

    init {
        loadAgency()
    }

    private fun loadAgency() {
        getAgencyDetailUseCase.execute(agencyId)
            .onEach { agency ->
                _state.update { it.copy(
                    isLoading = false,
                    agency = agency
                ) }
            }
            .launchIn(viewModelScope)
    }
}
