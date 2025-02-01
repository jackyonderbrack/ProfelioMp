package com.miluconnect.profeliomp.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProjectDto(
    @SerialName("id") val id: String,
    @SerialName("title") val title: String,
    @SerialName("customer") val customer: String,
    @SerialName("start_date") val startDate: String,
    @SerialName("end_date") val endDate: String,
    @SerialName("city") val city: String,
    @SerialName("status") val status: String,
    @SerialName("labels") val labels: List<String>,
    @SerialName("media") val media: MediaDto?
)