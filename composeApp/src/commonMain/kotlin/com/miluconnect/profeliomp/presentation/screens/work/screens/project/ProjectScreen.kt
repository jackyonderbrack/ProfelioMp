package com.miluconnect.profeliomp.presentation.screens.work.screens.project

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
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
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Button(
            onClick = { onIntent(ProjectIntent.GetProject(projectId)) }
        ) {
            Text("Get the data")
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .heightIn(200.dp, 300.dp),
            elevation = CardDefaults.elevatedCardElevation(8.dp),
            shape = RoundedCornerShape(8.dp),
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Project title: ${state.project?.title}", style = MaterialTheme.typography.headlineSmall)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Project id: ${state.project?.id}", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }

}