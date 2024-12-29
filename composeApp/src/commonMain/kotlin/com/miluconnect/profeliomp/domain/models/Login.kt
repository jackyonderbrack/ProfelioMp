package com.miluconnect.profeliomp.domain.models

data class LoginPayload(
    val username: String,
    val password: String
)

data class LoginResponse(
    val message: String,
    val token: String,
    val refreshToken: String,
)