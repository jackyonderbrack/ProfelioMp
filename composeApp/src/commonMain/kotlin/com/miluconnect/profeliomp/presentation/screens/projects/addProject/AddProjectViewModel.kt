package com.miluconnect.profeliomp.presentation.screens.projects.addProject

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AddProjectViewModel : ViewModel() {

    private val _state = MutableStateFlow(AddProjectState())
    val state: StateFlow<AddProjectState> get() = _state
}