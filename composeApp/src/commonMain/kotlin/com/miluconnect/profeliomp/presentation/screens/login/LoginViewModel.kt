package com.miluconnect.profeliomp.presentation.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miluconnect.profeliomp.domain.core.onError
import com.miluconnect.profeliomp.domain.core.onSuccess
import com.miluconnect.profeliomp.domain.models.login.LoginPayload
import com.miluconnect.profeliomp.data.repository.login.LoginRepository
import com.miluconnect.profeliomp.presentation.core.toUiText
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginRepository: LoginRepository
) : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> get() = _state

    fun onIntent(intent: LoginIntent) {
        when (intent) {
            is LoginIntent.UpdateUsername -> {
                _state.value = _state.value.copy(username = intent.username)
            }
            is LoginIntent.UpdatePassword -> {
                _state.value = _state.value.copy(password = intent.password)
            }
            is LoginIntent.LoginToApp -> {
                viewModelScope.launch {
                    _state.update { it.copy(isLoading = true) }
                        loginRepository
                            .login(loginPayload = LoginPayload(_state.value.username, _state.value.password))
                            .onSuccess { response ->
                                _state.update { it.copy(
                                    isLoading = false,
                                    errorMessage = null,
                                    responseMessage = response.message,
                                    token = response.token,
                                    refreshToken = response.refreshToken
                                ) }
                            }
                            .onError { response ->
                                _state.update { it.copy(
                                    isLoading = false,
                                    errorMessage = response.toUiText(),
                                    responseMessage = "Error",
                                    token = "Brak",
                                    refreshToken = "Brak",
                                ) }
                            }
                }
            }
        }
    }
}