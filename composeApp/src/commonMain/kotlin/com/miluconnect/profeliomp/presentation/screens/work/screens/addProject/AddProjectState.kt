package com.miluconnect.profeliomp.presentation.screens.work.screens.addProject

import com.miluconnect.profeliomp.domain.models.Project
import com.miluconnect.profeliomp.presentation.core.UiText

data class AddProjectState(
    val isLoading: Boolean = false,
    val successMessage: Project? = null,
    val errorMessage: UiText? = null,
)