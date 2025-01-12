package com.miluconnect.profeliomp.domain.models

data class Project(
    val id: String?,
    val title: String,
    val owner: String,
    val startDate: String,
    val city: String,
    val status: String?,
)

enum class ProjectRecurrence {
    DAILY, WEEKLY, MONTHLY
}

fun getProjectRecurrenceList(): List<ProjectRecurrence> {
    val projectRecurrenceList = mutableListOf<ProjectRecurrence>()
    projectRecurrenceList.add(ProjectRecurrence.DAILY)
    projectRecurrenceList.add(ProjectRecurrence.WEEKLY)
    projectRecurrenceList.add(ProjectRecurrence.MONTHLY)

    return projectRecurrenceList
}

