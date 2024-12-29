package com.miluconnect.profeliomp.presentation.screens.account

import androidx.lifecycle.ViewModel
import com.miluconnect.profeliomp.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AccountViewModel(
    private val userRepository: UserRepository
) : ViewModel() {
    private val _state = MutableStateFlow(AccountState())
    val state: StateFlow<AccountState> get() = _state
}