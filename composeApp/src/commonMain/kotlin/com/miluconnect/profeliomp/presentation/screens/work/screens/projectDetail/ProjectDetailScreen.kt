package com.miluconnect.profeliomp.presentation.screens.work.screens.projectDetail

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ProjectDetailScreenRoot(
    viewModel: ProjectDetailViewModel = koinViewModel<ProjectDetailViewModel>(),
    navController: NavController,
    projectId: String,
) {
    val state by viewModel.state.collectAsState()
    ProjectDetailScreen(projectId = projectId)
}

@Composable
fun ProjectDetailScreen(projectId: String) {
    Text(text = "Project Details: $projectId")
}