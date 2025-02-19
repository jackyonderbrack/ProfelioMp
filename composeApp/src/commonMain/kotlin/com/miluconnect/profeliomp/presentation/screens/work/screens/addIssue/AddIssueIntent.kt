package com.miluconnect.profeliomp.presentation.screens.work.screens.addIssue

import com.miluconnect.profeliomp.domain.models.Issue

sealed class AddIssueIntent {
    data class SubmitForm(val newIssue: Issue) : AddIssueIntent()
    data object DismissForm : AddIssueIntent()
}