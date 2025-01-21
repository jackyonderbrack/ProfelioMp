package com.miluconnect.profeliomp.presentation.screens.work

sealed class WorkIntent {
    data object GetProjectsList : WorkIntent()
    data object GetIssuesList : WorkIntent()
    data class OnTabSelectedChange(val tabIndex: Int): WorkIntent()
}