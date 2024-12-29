package com.miluconnect.profeliomp.data.dto.login

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginDto(
    @SerialName("message") val message: String,
    @SerialName("token") val token: String,
    @SerialName("refresh_token") val refreshToken: String,
)