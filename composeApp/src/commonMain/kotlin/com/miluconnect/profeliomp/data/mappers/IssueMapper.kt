package com.miluconnect.profeliomp.data.mappers

import com.miluconnect.profeliomp.data.dto.IssueDto
import com.miluconnect.profeliomp.domain.models.Issue

fun IssueDto.toIssueModel(): Issue {
    return Issue(
        id = id,
        title = title,
        projectId = projectId,
        createdAt = createdAt,
        dueTo = dueTo,
        storyPoint = storyPoint,
        status = status,
        labels = labels
    )
}