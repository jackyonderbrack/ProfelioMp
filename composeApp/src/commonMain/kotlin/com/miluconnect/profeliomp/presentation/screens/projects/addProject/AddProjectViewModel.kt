package com.miluconnect.profeliomp.presentation.screens.projects.addProject

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

class AddProjectViewModel(
    private val projectRepository: ProjectRepository
) : ViewModel() {

    private val _state = MutableStateFlow(AddProjectState())
    val state: StateFlow<AddProjectState> get() = _state

    fun onIntent(intent: AddProjectIntent) {
        when (intent) {
            is AddProjectIntent.AddNewProject -> {
                viewModelScope.launch {
                    _state.update { it.copy(isLoading = true) }
                    projectRepository
                        .createNewProject()
                        .onSuccess { result ->
                            delay(1000)
                            _state.update { it.copy(
                                isLoading = false,
                                newProjectData = result
                            ) }
                        }
                        .onError { error ->
                            delay(1000)
                            _state.update { it.copy(
                                isLoading = false
                            ) }
                            println(error)
                        }
                }
            }
        }
    }
}