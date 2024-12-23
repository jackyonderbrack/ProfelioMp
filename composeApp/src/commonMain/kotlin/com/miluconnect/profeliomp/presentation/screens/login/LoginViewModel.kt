package com.miluconnect.profeliomp.presentation.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val _state = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> get() = _state

    fun handleIntent(intent: LoginIntent) {
        when (intent) {
            is LoginIntent.UpdateUsername -> {
                _state.value = _state.value.copy(username = intent.username)
            }
            is LoginIntent.UpdatePassword -> {
                _state.value = _state.value.copy(password = intent.password)
            }
            LoginIntent.Login -> {
                viewModelScope.launch {
                    _state.value = _state.value.copy(isLoading = true)
                    // request
                    _state.value = _state.value.copy(isLoading = false)
                }
            }
        }
    }
}