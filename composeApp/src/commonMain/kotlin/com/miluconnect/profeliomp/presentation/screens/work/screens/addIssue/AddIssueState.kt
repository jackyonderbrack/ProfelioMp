package com.miluconnect.profeliomp.presentation.screens.work.screens.addIssue

import com.miluconnect.profeliomp.domain.models.Issue
import com.miluconnect.profeliomp.presentation.core.UiText

data class AddIssueState(
    val isLoading: Boolean = false,
    val successMessage: Issue? = null,
    val errorMessage: UiText? = null,
)