package com.miluconnect.profeliomp.presentation.screens.projects

import com.miluconnect.profeliomp.presentation.core.UiText

data class ProjectsState(
    val userName: String = "",
    val isLoading: Boolean = false,
    val errorMessage: UiText? = null,
)
