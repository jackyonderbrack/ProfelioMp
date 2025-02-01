package com.miluconnect.profeliomp.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Project(
    val id: String? = null,
    val title: String,
    val customer: String,
    val startDate: String? = null,
    val endDate: String,
    val city: String,
    val status: String? = null,
    val labels: List<String>? = emptyList(),
    val media: Media? = null,
)