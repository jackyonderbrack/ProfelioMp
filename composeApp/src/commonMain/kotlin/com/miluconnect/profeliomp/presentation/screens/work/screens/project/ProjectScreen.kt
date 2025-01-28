package com.miluconnect.profeliomp.presentation.screens.work.screens.project

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ProjectDetailScreenRoot(
    viewModel: ProjectViewModel = koinViewModel<ProjectViewModel>(),
    navController: NavController,
    projectId: String,
) {
    val state by viewModel.state.collectAsState()
    ProjectDetailScreen(
        state = state,
//        viewModel = viewModel,
        projectId = projectId,
        onIntent = { intent ->
            when (intent) {
                is ProjectIntent.GetProject -> viewModel.onIntent(intent)
            }
        }
    )
}

@Composable
fun ProjectDetailScreen(
    state: ProjectDetailsState,
//    viewModel: ProjectViewModel,
    projectId: String,
    onIntent: (ProjectIntent) -> Unit,
) {

    Button(
      onClick = { onIntent(ProjectIntent.GetProject(projectId)) }
    ) {
        Text("Get the data")
    }
    Text(text = "Project Details: ${state.project?.title}")
}