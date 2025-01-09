package com.miluconnect.profeliomp.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProjectDto(
    @SerialName("id") val id: String,
    @SerialName("title") val title: String,
    @SerialName("start_date") val startDate: String,
    @SerialName("city") val city: String,
    @SerialName("status") val status: String,
)