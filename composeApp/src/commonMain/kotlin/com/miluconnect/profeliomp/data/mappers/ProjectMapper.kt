package com.miluconnect.profeliomp.data.mappers

import com.miluconnect.profeliomp.data.dto.ProjectDto
import com.miluconnect.profeliomp.domain.models.Project

fun ProjectDto.toProjectModel(): Project {
    return Project(
        id = id,
        title = title,
        startDate = startDate,
        city = city,
        status = status
    )
}