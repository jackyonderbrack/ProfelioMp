package com.miluconnect.profeliomp.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponseDto(
    @SerialName("message") val message: String,
    @SerialName("token") val token: String,
    @SerialName("refresh_token") val refreshToken: String,
)