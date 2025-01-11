package com.miluconnect.profeliomp.presentation.screens.projects.addProject

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
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
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text("Add new project screen")
    }
}