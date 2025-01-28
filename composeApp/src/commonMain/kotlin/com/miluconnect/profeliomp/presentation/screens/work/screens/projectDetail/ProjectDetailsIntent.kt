package com.miluconnect.profeliomp.presentation.screens.work.screens.projectDetail

sealed class ProjectDetailsIntent {
    data class GetProjectDetails(val id: String) : ProjectDetailsIntent()
    data object GoBack : ProjectDetailsIntent()
}