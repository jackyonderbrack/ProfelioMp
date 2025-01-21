package com.miluconnect.profeliomp.presentation.screens.work.addProject

import com.miluconnect.profeliomp.domain.models.Project

sealed class AddProjectIntent {
    data class SubmitForm(val newProjectData: Project) : AddProjectIntent()
    data object DismissForm : AddProjectIntent()
}