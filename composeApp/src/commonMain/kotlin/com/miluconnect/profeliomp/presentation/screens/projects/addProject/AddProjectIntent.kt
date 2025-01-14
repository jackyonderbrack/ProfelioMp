package com.miluconnect.profeliomp.presentation.screens.projects.addProject

import com.miluconnect.profeliomp.domain.models.Project

sealed class AddProjectIntent {
    data class AddNewProject(val newProjectData: Project?) : AddProjectIntent()
}