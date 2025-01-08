package com.miluconnect.profeliomp.presentation.screens.blackboard

import androidx.lifecycle.ViewModel
import com.miluconnect.profeliomp.data.repository.preferences.PreferencesRepository
import com.miluconnect.profeliomp.data.repository.user.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class BlackboardViewModel(
    private val userRepository: UserRepository,
    private val preferencesRepository: PreferencesRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(BlackboardState())
    val state: StateFlow<BlackboardState> get() = _state
}