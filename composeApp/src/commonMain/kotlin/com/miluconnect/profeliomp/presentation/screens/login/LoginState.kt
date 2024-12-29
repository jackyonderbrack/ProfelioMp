package com.miluconnect.profeliomp.presentation.screens.login

import com.miluconnect.profeliomp.presentation.core.UiText

data class LoginState(
    val username: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val errorMessage: UiText? = null,
    val responseMessage: String? = null,
    val token: String? = null,
    val refreshToken: String? = null,
)