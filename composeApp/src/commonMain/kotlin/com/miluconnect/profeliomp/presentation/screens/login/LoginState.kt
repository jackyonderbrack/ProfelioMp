package com.miluconnect.profeliomp.presentation.screens.login

data class LoginState(
    val username: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val responseMessage: String? = null,
    val token: String? = null,
    val refreshToken: String? = null,
)