package com.miluconnect.profeliomp.presentation.screens.projects.addProject

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.miluconnect.profeliomp.presentation.components.AddProjectForm
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AddProjectScreenRoot(
    viewModel: AddProjectViewModel = koinViewModel<AddProjectViewModel>(),
) {

    val state by viewModel.state.collectAsState()

    AddProjectScreen(
        state = state
    )
}

@Composable
fun AddProjectScreen(
    state: AddProjectState
) {
    AddProjectForm()
}