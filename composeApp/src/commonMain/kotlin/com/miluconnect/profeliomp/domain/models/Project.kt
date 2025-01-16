package com.miluconnect.profeliomp.domain.models

data class Project(
    val id: String? = null,
    val title: String,
    val users: List<String>,
    val startDate: String? = null,
    val endDate: String,
    val city: String,
    val status: String? = null,
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

