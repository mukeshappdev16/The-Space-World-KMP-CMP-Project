package com.appdev16.thespaceworld.presentation.screens.agencies.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appdev16.thespaceworld.domain.modal.agencies.Agency
import com.appdev16.thespaceworld.domain.usecase.GetAgencyDetailUseCase
import com.appdev16.thespaceworld.util.NetworkError
import com.appdev16.thespaceworld.util.Result
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class AgencyDetailUiState(
    val isLoading: Boolean = true,
    val agency: Agency? = null,
    val error: NetworkError? = null
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

    fun retry() {
        loadAgency()
    }

    private fun loadAgency() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            getAgencyDetailUseCase.execute(agencyId)
                .collect { result ->
                    when (result) {
                        is Result.Error -> {
                            _state.update { it.copy(
                                isLoading = false,
                                error = result.error
                            ) }
                        }
                        is Result.Success -> {
                            _state.update { it.copy(
                                isLoading = false,
                                agency = result.data,
                                error = null
                            ) }
                        }
                    }
                }
        }
    }
}
