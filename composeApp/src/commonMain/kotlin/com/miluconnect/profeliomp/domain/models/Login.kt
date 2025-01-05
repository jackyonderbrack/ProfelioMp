package com.miluconnect.profeliomp.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class LoginPayload(
    val username: String,
    val password: String
)

@Serializable
data class LoginResponse(
    val message: String,
    val token: String,
    val refreshToken: String,
)