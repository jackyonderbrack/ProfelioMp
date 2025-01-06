package com.miluconnect.profeliomp.presentation.screens.account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miluconnect.profeliomp.data.repository.preferences.PreferencesRepository
import com.miluconnect.profeliomp.data.repository.user.UserRepository
import com.miluconnect.profeliomp.domain.core.onSuccess
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
            userRepository.getCurrentUser()
                .onSuccess { userData ->
                    _state.update { it.copy(
                        userId = userData.id,
                        userEmail = userData.email,
                        userName = userData.name
                    ) }
                }
        }
    }

    fun onIntent(intent: AccountIntent) {
        when (intent) {
            AccountIntent.GetCurrentUser -> {}
            AccountIntent.Logout -> {
                viewModelScope.launch {
                    _state.update { it.copy(isLoading = true) }
                    preferencesRepository.clearPreferences()
                    _state.update { it.copy(isLoading = false) }
                }
            }
        }
    }
}