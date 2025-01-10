package com.miluconnect.profeliomp.presentation.screens.projects

import com.miluconnect.profeliomp.domain.models.Project
import com.miluconnect.profeliomp.presentation.core.UiText

data class ProjectsState(
    val isLoading: Boolean = false,
    val errorMessage: UiText? = null,

    val projectsList: List<Project> = emptyList(),
)
