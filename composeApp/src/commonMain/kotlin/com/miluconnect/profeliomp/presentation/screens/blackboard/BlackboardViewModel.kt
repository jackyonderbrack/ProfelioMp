package com.miluconnect.profeliomp.presentation.screens.blackboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miluconnect.profeliomp.data.repository.offer.OfferRepository
import com.miluconnect.profeliomp.domain.core.onError
import com.miluconnect.profeliomp.domain.core.onSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BlackboardViewModel(
    private val offerRepository: OfferRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(BlackboardState())
    val state: StateFlow<BlackboardState> get() = _state

    init {
        getAllOffersList()
    }

    private fun getAllOffersList() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            offerRepository
                .getAllOffers()
                .onSuccess { result ->
                    _state.update { it.copy(
                        isLoading = false,
                        offersList = result
                    ) }
                }
                .onError {
                    println("Error: $it")
                }
        }
    }
}