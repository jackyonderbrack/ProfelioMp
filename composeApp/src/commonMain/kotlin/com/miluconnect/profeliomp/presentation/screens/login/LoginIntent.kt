package com.miluconnect.profeliomp.presentation.screens.login

sealed class LoginIntent {
    data class UpdateUsername(val username: String) : LoginIntent()
    data class UpdatePassword(val password: String) : LoginIntent()
    object Login : LoginIntent()
}