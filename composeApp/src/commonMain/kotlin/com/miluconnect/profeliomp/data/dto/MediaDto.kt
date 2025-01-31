package com.miluconnect.profeliomp.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MediaDto (
    @SerialName("id") val id: String,
    @SerialName("related_id") val relatedId: String,
    @SerialName("file_name")val fileName: String,
    @SerialName("file_path")val filePath: String,
    @SerialName("mime_type")val mimeType: String,
    @SerialName("created_at")val createdAt: String
)

