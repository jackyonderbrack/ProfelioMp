package com.miluconnect.profeliomp.data.user.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    @SerialName("id") val id: String,
    @SerialName("name") val name: String,
    @SerialName("email") val email: String,
    @SerialName("account_type") val accountType: String,
    @SerialName("is_admin") val isAdmin: Boolean,
)