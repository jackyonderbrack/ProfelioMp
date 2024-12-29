package com.miluconnect.profeliomp.presentation.screens.login

import com.miluconnect.profeliomp.domain.models.LoginPayload

sealed class LoginIntent {
    data class UpdateUsername(val username: String) : LoginIntent()
    data class UpdatePassword(val password: String) : LoginIntent()
    data class LoginToApp(val loginPayload: LoginPayload) : LoginIntent()
}