package com.miluconnect.profeliomp.presentation.components.projectsTabs

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class ProjectsTabsViewModel() : ViewModel() {

    private val _state = MutableStateFlow(ProjectsTabsState())
    val state: StateFlow<ProjectsTabsState> get() = _state

    fun onIntent(intent: ProjectsTabsIntent) {
        when (intent) {
            is ProjectsTabsIntent.OnTabSelectedChange -> {
                _state.update { it.copy(selectedTabIndex = intent.tabIndex) }
            }
        }
    }
}