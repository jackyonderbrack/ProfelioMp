package com.miluconnect.profeliomp.presentation.screens.projects

import com.miluconnect.profeliomp.domain.models.Project
import com.miluconnect.profeliomp.presentation.core.UiText

data class ProjectsState(
    val isLoading: Boolean = true,
    val errorMessage: UiText? = null,
    val selectedTabIndex: Int = 0,
    val projectsList: List<Project> = emptyList(),
)
