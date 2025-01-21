package com.miluconnect.profeliomp.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IssueDto(
    @SerialName("id") val id: String,
    @SerialName("title") val title: String,
    @SerialName("projectId") val projectId: String,
    @SerialName("created_at") val createdAt: String,
    @SerialName("due_to") val dueTo: String,
    @SerialName("story_point") val storyPoint: String,
    @SerialName("status") val status: String,
    @SerialName("labels") val labels: List<String>,
)