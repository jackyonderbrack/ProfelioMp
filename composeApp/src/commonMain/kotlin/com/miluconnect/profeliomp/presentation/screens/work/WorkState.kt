package com.miluconnect.profeliomp.presentation.screens.work

import com.miluconnect.profeliomp.domain.models.Issue
import com.miluconnect.profeliomp.domain.models.Project
import com.miluconnect.profeliomp.presentation.core.UiText

data class WorkState(
    val isLoading: Boolean = true,
    val errorMessage: UiText? = null,
    val selectedTabIndex: Int = 0,
    val projectsList: List<Project> = emptyList(),
    val issuesList: List<Issue> = emptyList()
)
