package com.miluconnect.profeliomp.presentation.screens.account

import com.miluconnect.profeliomp.presentation.core.UiText

data class AccountState(
    val userId: String = "",
    val userName: String = "",
    val userEmail: String = "",
    val isLoading: Boolean = false,
    val errorMessage: UiText? = null,
)