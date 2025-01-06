package com.miluconnect.profeliomp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miluconnect.profeliomp.data.repository.login.LoginRepository
import com.miluconnect.profeliomp.data.repository.preferences.PreferencesRepository
import com.miluconnect.profeliomp.presentation.screens.login.LoginIntent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
/**
 * Handles:
 * - [PreferencesRepository] tokens and navigation to manage [AppState].
 */
class AppViewModel(
    private val preferencesRepository: PreferencesRepository
) : ViewModel() {

    private val _state = MutableStateFlow(AppState())
    val state: StateFlow<AppState> get() = _state

    init {
        getPreferencesToken()
    }

    /**
     * Handle [AppState] and Navigation within it.
     * */
    private fun getPreferencesToken() {
        viewModelScope.launch {
            preferencesRepository.getToken().collect { preferencesToken ->
                _state.update { it.copy(
                    token = preferencesToken,
                ) }
            }
        }
    }

    /**
     * Handle Logout purposes mainly.
     * */
    fun clearPreferences() {
        viewModelScope.launch {
            preferencesRepository.clearPreferences()
            _state.update { it.copy(token = null) } // redundant, for debug purposes
        }
    }
}