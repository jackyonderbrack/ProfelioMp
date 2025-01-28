package com.miluconnect.profeliomp.presentation.screens.work.screens.projectDetail

import com.miluconnect.profeliomp.domain.models.Project

sealed class ProjectDetailsIntent {
    data class SubmitForm(val newProjectData: Project) : ProjectDetailsIntent()
    data object DismissForm : ProjectDetailsIntent()
}