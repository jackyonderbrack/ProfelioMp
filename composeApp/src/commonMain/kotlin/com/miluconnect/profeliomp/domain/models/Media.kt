package com.miluconnect.profeliomp.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Media(
    val id: String,
    val relatedId: String,
    val fileName: String,
    val filePath: String,
    val mimeType: String,
    val createdAt: String,
    val fileBytes: ByteArray
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Media) return false

        if (id != other.id) return false
        if (relatedId != other.relatedId) return false
        if (fileName != other.fileName) return false
        if (filePath != other.filePath) return false
        if (mimeType != other.mimeType) return false
        if (createdAt != other.createdAt) return false
        if (!fileBytes.contentEquals(other.fileBytes)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + relatedId.hashCode()
        result = 31 * result + fileName.hashCode()
        result = 31 * result + filePath.hashCode()
        result = 31 * result + mimeType.hashCode()
        result = 31 * result + createdAt.hashCode()
        result = 31 * result + fileBytes.contentHashCode()
        return result
    }
}
