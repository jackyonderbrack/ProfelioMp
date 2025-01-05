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
     * This ViewModel manages the [state] and shares an observable state to [LoginScreenRoot].
     */
    private val _state = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> get() = _state

    /**
     * Handles intents sent from the [LoginScreen]:
     * - This function updates the state of the ViewModel based on the received [LoginIntent].
     * - Each intent corresponds to specific changes in the state or triggers an action.
     */
    fun onIntent(intent: LoginIntent) {
        when (intent) {
            /**
             * Handles [LoginIntent.UpdateUsername]:
             * - Updates the `username` field in the current [state].
             */
            is LoginIntent.UpdateUsername -> {
                _state.value = _state.value.copy(username = intent.username)
            }

            /**
             * Handles [LoginIntent.UpdatePassword]:
             * - Updates the `password` field in the current [state].
             */
            is LoginIntent.UpdatePassword -> {
                _state.value = _state.value.copy(password = intent.password)
            }

            /**
             * Handles [LoginIntent.LoginToApp]:
             * - Launches a coroutine within the `viewModelScope` to handle login.
             * - Updates the `isLoading` field to `true` while login is in progress.
             * - Interacts with the [LoginRepository] to authenticate the user.
             * - Updates the state based on the success or failure of the login process.
             */
            is LoginIntent.LoginToApp -> {
                viewModelScope.launch {
                    // Set loading state to true
                    _state.update { it.copy(isLoading = true) }

                    // Attempt to login via the repository
                    loginRepository
                        .login(loginPayload = LoginPayload(_state.value.username, _state.value.password))
                        .onSuccess { response ->
                            // Update state with success data
                            _state.update {
                                it.copy(
                                    isLoading = false,
                                    errorMessage = null,
                                    responseMessage = response.message,
                                    token = response.token,
                                    refreshToken = response.refreshToken
                                )
                            }
                        }
                        .onError { error ->
                            // Update state with error information
                            _state.update {
                                it.copy(
                                    isLoading = false,
                                    errorMessage = error.toUiText(),
                                    responseMessage = null,
                                    token = "No token provided",
                                    refreshToken = "No refresh token provided"
                                )
                            }
                        }
                }
            }
        }
    }
}
