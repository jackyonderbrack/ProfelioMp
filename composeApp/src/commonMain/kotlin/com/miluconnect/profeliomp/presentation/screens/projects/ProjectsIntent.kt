package com.miluconnect.profeliomp.presentation.screens.projects

sealed class ProjectsIntent {
    data object GetProjectsList : ProjectsIntent()
    data class OnTabSelectedChange(val tabIndex: Int): ProjectsIntent()
}