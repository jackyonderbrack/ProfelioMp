package com.miluconnect.profeliomp.data.mappers

import com.miluconnect.profeliomp.data.dto.MediaDto
import com.miluconnect.profeliomp.data.dto.ProjectDto
import com.miluconnect.profeliomp.domain.models.Media
import com.miluconnect.profeliomp.domain.models.Project

fun MediaDto.toMediaModel(): Media {
    return Media(
        id = id,
        relatedId = relatedId,
        fileName = fileName,
        filePath = filePath,
        mimeType = mimeType,
        createdAt = createdAt
    )
}