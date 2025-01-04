package com.miluconnect.profeliomp.presentation.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miluconnect.profeliomp.domain.core.onError
import com.miluconnect.profeliomp.domain.core.onSuccess
import com.miluconnect.profeliomp.domain.models.LoginPayload
import com.miluconnect.profeliomp.data.repository.login.LoginRepository
import com.miluconnect.profeliomp.presentation.core.toUiText
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginRepository: LoginRepository
) : ViewModel() {

    /**
     * This ViewModel changes [state] and share observable to [LoginScreenRoot]
     * */
    private val _state = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> get() = _state

    /**
     * public function *onIntent(*intent) is used in [LoginScreen] to change sealed intents of [LoginIntent].
     * Invocation with *intent passed inside changes [state] of viewModel.
     */
    fun onIntent(intent: LoginIntent) {
        when (intent) {
            /**
             * This passed *intent of [LoginIntent] updates username field [state]
             * */
            is LoginIntent.UpdateUsername -> {
                _state.value = _state.value.copy(username = intent.username)
            }

            /**
             * This passed *intent of [LoginIntent] updates password field [state]
             * */
            is LoginIntent.UpdatePassword -> {
                _state.value = _state.value.copy(password = intent.password)
            }

            /**
             * This passed *intent of [LoginIntent] handles:
             * 1. launches a viewModelScope coroutine,
             * 2. updates [state] of isLoading,
             * 3. in scope runs [LoginRepository] with login() impl passed [LoginPayload] inside,
             * 4. waits till coroutine ends with result and updates [state] with these results.
             * */
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