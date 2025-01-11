package com.miluconnect.profeliomp.presentation.screens.projects.addProject

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AddProjectScreenRoot(
    viewModel: AddProjectViewModel = koinViewModel<AddProjectViewModel>()
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
    Text("Add new project screen")
}