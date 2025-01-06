package com.miluconnect.profeliomp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miluconnect.profeliomp.data.repository.preferences.PreferencesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AppViewModel(
    private val preferencesRepository: PreferencesRepository
) : ViewModel() {

    private val _state = MutableStateFlow(AppState())
    val state: StateFlow<AppState> get() = _state

    init {
        viewModelScope.launch {
            preferencesRepository.getToken().collect { preferencesToken ->
                _state.update { it.copy(
                    token = preferencesToken,
                    isLoggedIn = preferencesToken != null
                ) }
            }
        }
    }

    fun clearToken() {
        viewModelScope.launch {
            preferencesRepository.clearPreferences()
            _state.update { it.copy(token = null) } // redundant, for debug purposes
        }
    }
}