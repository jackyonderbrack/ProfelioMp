package com.miluconnect.profeliomp.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Media(
    val id: String,
    val relatedId: String,
    val fileName: String,
    val filePath: String,
    val mimeType: String,
    val createdAt: String
)
