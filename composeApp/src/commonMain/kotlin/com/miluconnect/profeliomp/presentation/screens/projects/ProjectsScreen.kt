package com.miluconnect.profeliomp.presentation.screens.projects

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ProjectsScreenRoot(
    viewModel: ProjectsViewModel = koinViewModel<ProjectsViewModel>()
) {

    val state by viewModel.state.collectAsState()

    ProjectsScreen(
        state = state
    )
}

@Composable
private fun ProjectsScreen(
    state: ProjectsState,
) {
    Column {
        Text(text = state.userName)
        state.errorMessage?.let {
            Text(text = it.asString())
        }
    }
}