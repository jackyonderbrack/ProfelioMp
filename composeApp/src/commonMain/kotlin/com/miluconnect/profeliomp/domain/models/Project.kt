package com.miluconnect.profeliomp.domain.models

data class Project(
    val id: String,
    val title: String,
    val owner: String,
    val startDate: String,
    val city: String,
    val status: String,
)