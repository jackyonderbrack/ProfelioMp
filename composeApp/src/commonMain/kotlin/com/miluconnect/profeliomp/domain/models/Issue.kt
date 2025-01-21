package com.miluconnect.profeliomp.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Issue(
    val id: String? = null,
    val title: String,
    val projectId: String,
    val createdAt: String? = null,
    val dueTo: String,
    val storyPoint: String,
    val status: String? = null,
    val labels: List<String>? = emptyList()
)