package com.miluconnect.profeliomp.presentation.screens.projects

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miluconnect.profeliomp.data.repository.project.ProjectRepository
import com.miluconnect.profeliomp.domain.core.onError
import com.miluconnect.profeliomp.domain.core.onSuccess
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProjectsViewModel(
    private val projectRepository: ProjectRepository
) : ViewModel() {

    private val _state = MutableStateFlow(ProjectsState())
    val state: StateFlow<ProjectsState> get() = _state

    fun onIntent(intent: ProjectsIntent) {
        when (intent) {
            is ProjectsIntent.OnTabSelectedChange -> {
                _state.update { it.copy(selectedTabIndex = intent.tabIndex) }
            }

            is ProjectsIntent.GetProjectsList -> {
                _state.update { it.copy(isLoading = true) }
                getAllOffersList()
            }
        }
    }

    private fun getAllOffersList() {
        viewModelScope.launch {
            projectRepository
                .getAllProjects()
                .onSuccess { result ->
                    delay(1000)
                    _state.update { it.copy(
                        isLoading = false,
                        projectsList = result
                    ) }
                }
                .onError { errorResult ->
                    println("Error: $errorResult")
                    delay(1000)
                    _state.update { it.copy(
                        isLoading = false,
                        errorMessage = it.errorMessage
                    ) }
                }

        }
    }
}

