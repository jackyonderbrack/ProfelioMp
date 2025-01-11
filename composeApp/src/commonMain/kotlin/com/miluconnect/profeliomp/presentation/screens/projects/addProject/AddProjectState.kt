package com.miluconnect.profeliomp.presentation.screens.projects.addProject

import com.miluconnect.profeliomp.presentation.core.UiText

data class AddProjectState(
    val isLoading: Boolean = false,
    val errorMessage: UiText? = null,
)