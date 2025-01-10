package com.miluconnect.profeliomp.presentation.components.projectsTabs

sealed interface ProjectsTabsIntent {
    data class OnTabSelectedChange(val tabIndex: Int): ProjectsTabsIntent
}