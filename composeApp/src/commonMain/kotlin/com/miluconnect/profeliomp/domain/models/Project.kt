package com.miluconnect.profeliomp.domain.models

data class Project(
    val id: String?,
    val title: String,
    val owner: String,
    val startDate: String,
    val endDate: String,
    val city: String,
    val status: String?,
)

enum class ProjectRecurrence {
    Daily, Weekly, Monthly
}

fun getProjectRecurrenceList(): List<ProjectRecurrence> {
    val projectRecurrenceList = mutableListOf<ProjectRecurrence>()
    projectRecurrenceList.add(ProjectRecurrence.Daily)
    projectRecurrenceList.add(ProjectRecurrence.Weekly)
    projectRecurrenceList.add(ProjectRecurrence.Monthly)

    return projectRecurrenceList
}

