package com.miluconnect.profeliomp.data.dto

import com.miluconnect.profeliomp.domain.models.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProjectDto(
    @SerialName("id") val id: String,
    @SerialName("title") val title: String,
    @SerialName("users") val users: List<UserDto>,
    @SerialName("start_date") val startDate: String,
    @SerialName("end_date") val endDate: String,
    @SerialName("city") val city: String,
    @SerialName("status") val status: String,
)