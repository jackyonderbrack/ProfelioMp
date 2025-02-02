package com.miluconnect.profeliomp.presentation.screens.work.screens.addProject

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miluconnect.profeliomp.data.repository.media.MediaRepository
import com.miluconnect.profeliomp.data.repository.project.ProjectRepository
import com.miluconnect.profeliomp.domain.core.DataResult
import com.miluconnect.profeliomp.domain.core.onError
import com.miluconnect.profeliomp.domain.core.onSuccess
import com.miluconnect.profeliomp.presentation.core.toUiText
import com.miluconnect.profeliomp.presentation.utils.convertUriToByteArray
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AddProjectViewModel(
    private val projectRepository: ProjectRepository,
    private val mediaRepository: MediaRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(AddProjectState())
    val state: StateFlow<AddProjectState> get() = _state

    fun onIntent(intent: AddProjectIntent, onNavigateBack: () -> Unit) {
        when (intent) {
            is AddProjectIntent.SubmitNewProject -> {
                viewModelScope.launch {
                    _state.update { it.copy(isLoading = true) }
                    var projectToSubmit = intent.newProjectData

                    // Jeśli użytkownik wybrał obraz, próbujemy go przetworzyć i wysłać:
                    if (intent.selectedProjectImage != null) {
                        val imageBytes = convertUriToByteArray(intent.selectedProjectImage)
                        if (imageBytes != null) {
                            when (val uploadResult = mediaRepository.uploadImage(imageBytes)) {
                                is DataResult.Success -> {
                                    projectToSubmit = projectToSubmit.copy(picture = uploadResult.data.filePath)
                                }
                                is DataResult.Error -> {
                                    _state.update {
                                        it.copy(
                                            isLoading = false,
                                            errorMessage = uploadResult.error.toUiText()
                                        )
                                    }
                                    return@launch
                                }
                            }
                        } else {
                            _state.update {
                                it.copy(
                                    isLoading = false,
                                    errorMessage = it.errorMessage
                                )
                            }
                            return@launch
                        }
                    }

                    // Następnie tworzymy projekt z uzupełnionymi danymi (w tym URL-em obrazu, jeśli upload się powiódł)
                    projectRepository.createNewProject(projectToSubmit)
                        .onSuccess { result ->
                            delay(1000)
                            _state.update { it.copy(
                                isLoading = false,
                                successMessage = result
                            ) }
                            onNavigateBack()
                        }
                        .onError { error ->
                            delay(1000)
                            _state.update { it.copy(
                                isLoading = false,
                                errorMessage = error.toUiText()
                            ) }
                        }
                }
            }
            is AddProjectIntent.DismissForm -> onNavigateBack()
        }
    }
}
