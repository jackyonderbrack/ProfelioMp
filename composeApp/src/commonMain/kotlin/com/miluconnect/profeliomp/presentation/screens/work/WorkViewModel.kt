package com.miluconnect.profeliomp.presentation.screens.work

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miluconnect.profeliomp.data.repository.issue.IssueRepository
import com.miluconnect.profeliomp.data.repository.project.ProjectRepository
import com.miluconnect.profeliomp.domain.core.onError
import com.miluconnect.profeliomp.domain.core.onSuccess
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WorkViewModel(
    private val projectRepository: ProjectRepository,
    private val issuesRepository: IssueRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(WorkState())
    val state: StateFlow<WorkState> get() = _state

    fun onIntent(intent: WorkIntent) {
        when (intent) {
            is WorkIntent.OnTabSelectedChange -> {
                _state.update { it.copy(selectedTabIndex = intent.tabIndex) }
            }

            is WorkIntent.GetProjectsList -> {
                _state.update { it.copy(isLoading = true) }
                getAllOffersList()
            }

            is WorkIntent.GetIssuesList -> {
                _state.update { it.copy(isLoading = true) }
                getAllIssuesList()
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

    private fun getAllIssuesList() {
        viewModelScope.launch {
            issuesRepository
                .getAllIssues()
                .onSuccess { result ->
                    delay(1000)
                    _state.update { it.copy(
                        isLoading = false,
                        issuesList = result
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

