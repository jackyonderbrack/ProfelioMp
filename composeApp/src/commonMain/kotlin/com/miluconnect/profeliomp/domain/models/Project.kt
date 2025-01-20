package com.miluconnect.profeliomp.domain.models

data class Project(
    val id: String? = null,
    val title: String,
    val customer: String,
    val startDate: String? = null,
    val endDate: String,
    val city: String,
    val status: String? = null,
    val labels: List<String>? = emptyList()
)