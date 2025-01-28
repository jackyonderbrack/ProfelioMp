package com.miluconnect.profeliomp.presentation.screens.work.screens.projectDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miluconnect.profeliomp.data.repository.project.ProjectRepository
import com.miluconnect.profeliomp.domain.core.onError
import com.miluconnect.profeliomp.domain.core.onSuccess
import com.miluconnect.profeliomp.presentation.core.toUiText
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProjectDetailViewModel(
    private val projectRepository: ProjectRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(ProjectDetailsState())
    val state: StateFlow<ProjectDetailsState> get() = _state

    fun onIntent(intent: ProjectDetailsIntent, onNavigateBack: () -> Unit) {
        when (intent) {
            is ProjectDetailsIntent.GetProjectDetails -> {
                viewModelScope.launch {
                    _state.update { it.copy(isLoading = true) }
                    projectRepository
                        .getProjectDetails(
                            intent.id
                        )
                        .onSuccess { result ->
                            delay(1000)
                            _state.update { it.copy(
                                isLoading = false,
                                projectDetails = result
                            ) }
                            onNavigateBack()
                        }
                        .onError { error ->
                            delay(1000)
                            _state.update { it.copy(
                                isLoading = false,
                                errorMessage = error.toUiText()
                            ) }
                            println(error)
                        }
                }
            }

            ProjectDetailsIntent.GoBack -> onNavigateBack()
        }
    }
}