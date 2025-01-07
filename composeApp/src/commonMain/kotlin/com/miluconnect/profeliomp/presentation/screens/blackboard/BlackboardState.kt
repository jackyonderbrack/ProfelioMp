package com.miluconnect.profeliomp.presentation.screens.blackboard

import com.miluconnect.profeliomp.presentation.core.UiText

data class BlackboardState(
    val userName: String = "",
    val isLoading: Boolean = false,
    val errorMessage: UiText? = null,
)
