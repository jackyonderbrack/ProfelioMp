package com.miluconnect.profeliomp.presentation.screens.projects

sealed class ProjectsIntent {
    data object GetProjects : ProjectsIntent()
    data class SelectProject(val projectId: String) : ProjectsIntent()
}