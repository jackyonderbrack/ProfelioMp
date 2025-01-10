package com.miluconnect.profeliomp.presentation.components.projectsTabs

import com.miluconnect.profeliomp.presentation.core.UiText

data class ProjectsTabsState(
    val isLoading: Boolean = false,
    val errorMessage: UiText? = null,
    val selectedTabIndex: Int = 0,
    )
