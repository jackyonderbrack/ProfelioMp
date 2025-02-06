package com.miluconnect.profeliomp.presentation.screens.work.screens.project

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedSuggestionChip
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ProjectDetailScreenRoot(
    viewModel: ProjectViewModel = koinViewModel<ProjectViewModel>(),
    projectId: String,
    popStackBack: () -> Unit,
) {
    val state by viewModel.state.collectAsState()
    ProjectDetailScreen(
        state = state,
        projectId = projectId,
        onIntent = { intent ->
            when (intent) {
                is ProjectIntent.GetProject -> viewModel.onIntent(intent)
                is ProjectIntent.DeleteProject -> viewModel.onIntent(intent) {
                    popStackBack()
                }
            }
        }
    )
}

@Composable
fun ProjectDetailScreen(
    state: ProjectState,
    projectId: String,
    onIntent: (ProjectIntent) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp).verticalScroll(rememberScrollState())
    ) {
        Button(
            onClick = { onIntent(ProjectIntent.GetProject(projectId)) }
        ) {
            Text("Get the data")
        }
        Button(
            onClick = { onIntent(ProjectIntent.DeleteProject(projectId)) }
        ) {
            Text("Delete")
        }

        /* PROJECT META ROW */
        Text(
            text = "Project Meta",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(160.dp, 320.dp),
            elevation = CardDefaults.elevatedCardElevation(8.dp),
            shape = RoundedCornerShape(8.dp),
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "${state.project?.title}", style = MaterialTheme.typography.headlineSmall)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Customer e-mail: ${state.project?.customer}", style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Project id: ${state.project?.id}", style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "City: ${state.project?.city}", style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Started at: ${state.project?.startDate}", style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Due to: ${state.project?.endDate}", style = MaterialTheme.typography.bodyMedium)
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    state.project?.labels?.forEach { label ->
                        ElevatedSuggestionChip(
                            label = { Text(text = label) },
                            onClick = {}
                        )
                    }
                }

                state.project?.status?.let { status ->
                    ElevatedSuggestionChip(
                        label = { Text(text = status) },
                        onClick = {}
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        /* ISSUES ROW */
        Text(
            text = "To do",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        Column(modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        ) {
            Column(
            ) {
                Card {
                    ListItem(
                        colors = ListItemDefaults.colors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant,
                        ),
                        headlineContent = { Text("Three line list item") },
                        overlineContent = { Text("OVERLINE") },
                        supportingContent = { Text("Secondary text") },
                        leadingContent = {
                            Icon(
                                Icons.Filled.Favorite,
                                contentDescription = "Localized description",
                            )
                        },
                        trailingContent = { Text("meta") }
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
                Card {
                    ListItem(
                        colors = ListItemDefaults.colors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant,
                        ),
                        headlineContent = { Text("Three line list item") },
                        overlineContent = { Text("OVERLINE") },
                        supportingContent = { Text("Secondary text") },
                        leadingContent = {
                            Icon(
                                Icons.Filled.Favorite,
                                contentDescription = "Localized description",
                            )
                        },
                        trailingContent = { Text("meta") }
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
                Card {
                    ListItem(
                        colors = ListItemDefaults.colors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant,
                        ),
                        headlineContent = { Text("Three line list item") },
                        overlineContent = { Text("OVERLINE") },
                        supportingContent = { Text("Secondary text") },
                        leadingContent = {
                            Icon(
                                Icons.Filled.Favorite,
                                contentDescription = "Localized description",
                            )
                        },
                        trailingContent = { Text("meta") }
                    )
                }
            }

        }
    }
}