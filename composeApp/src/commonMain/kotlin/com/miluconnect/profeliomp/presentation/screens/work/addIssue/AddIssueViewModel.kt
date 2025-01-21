package com.miluconnect.profeliomp.presentation.screens.work.addIssue

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miluconnect.profeliomp.data.repository.issue.IssueRepository
import com.miluconnect.profeliomp.domain.core.onError
import com.miluconnect.profeliomp.domain.core.onSuccess
import com.miluconnect.profeliomp.presentation.core.toUiText
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AddIssueViewModel(
    private val issueRepository: IssueRepository
) : ViewModel() {

    private val _state = MutableStateFlow(AddIssueState())
    val state: StateFlow<AddIssueState> get() = _state

    fun onIntent(intent: AddIssueIntent, onNavigateBack: () -> Unit) {
        when (intent) {
            is AddIssueIntent.SubmitForm -> {
                viewModelScope.launch {
                    _state.update { it.copy(isLoading = true) }
                    issueRepository
                        .createNewIssue(
                            intent.newIssue
                        )
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
                            println(error)
                        }
                }
            }

            AddIssueIntent.DismissForm -> onNavigateBack()
        }
    }
}