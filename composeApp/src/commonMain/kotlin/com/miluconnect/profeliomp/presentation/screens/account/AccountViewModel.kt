package com.miluconnect.profeliomp.presentation.screens.account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miluconnect.profeliomp.data.repository.preferences.PreferencesRepository
import com.miluconnect.profeliomp.data.repository.user.UserRepository
import com.miluconnect.profeliomp.domain.core.onError
import com.miluconnect.profeliomp.domain.core.onSuccess
import com.miluconnect.profeliomp.presentation.core.UiText
import com.miluconnect.profeliomp.presentation.core.toUiText
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AccountViewModel(
    private val userRepository: UserRepository,
    private val preferencesRepository: PreferencesRepository
) : ViewModel() {

    private val _state = MutableStateFlow(AccountState())
    val state: StateFlow<AccountState> get() = _state

    init {
        viewModelScope.launch {
            userRepository.getCurrentUserData()
                .onSuccess { userData ->
                    _state.update { it.copy(
                        userId = userData.id,
                        userEmail = userData.email!!,
                        userName = userData.name,
                        userRole = userData.role!!,
                    ) }
                }
        }
    }

    fun onIntent(intent: AccountIntent) {
        when (intent) {
            AccountIntent.GetCurrentUser -> {
                viewModelScope.launch {
                    _state.update { it.copy(isLoading = true) }
                    userRepository
                        .getCurrentUserData()
                        .onSuccess { response ->
                            _state.update { it.copy(
                                isLoading = false,
                                userId = response.id,
                                userName = response.name,
                                userEmail = response.email!!
                            ) }
                        }
                        .onError { error ->
                            _state.update { it.copy(
                                isLoading = false,
                                errorMessage = error.toUiText()
                            ) }

                        }
                }
            }
            AccountIntent.Logout -> {
                viewModelScope.launch {
                    preferencesRepository.clearPreferences()
                }
            }
        }
    }
}