package com.miluconnect.profeliomp.presentation.screens.projects

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.miluconnect.profeliomp.presentation.components.ScreenSurface
import com.miluconnect.profeliomp.presentation.components.chipsRow.ChipsRow
import com.miluconnect.profeliomp.presentation.screens.projects.components.ProjectsList
import com.miluconnect.profeliomp.presentation.screens.projects.components.ProjectsTabs
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ProjectsScreenRoot(
    viewModel: ProjectsViewModel = koinViewModel<ProjectsViewModel>(),
) {

    val state by viewModel.state.collectAsState()
    
    ProjectsScreen(
        state = state,
        viewModel = viewModel
    )
}

@Composable
private fun ProjectsScreen(
    state: ProjectsState,
    viewModel: ProjectsViewModel
) {
    val lazyProjectsListState = rememberLazyListState()
    val filterOptions = listOf("Ongoing", "Completed", "Archived", "Urgent", "Draft")
    var selectedFilter by remember { mutableStateOf("Ongoing") }
    var isDescending by remember { mutableStateOf(false) }

    val filteredAndSortedProjects = remember(state.projectsList, selectedFilter, isDescending) {
        state.projectsList
            .filter { project ->
                when (selectedFilter) {
                    "Ongoing" -> project.status == "Ongoing"
                    "Completed" -> project.status == "Completed"
                    "Archived" -> project.status == "Archived"
                    else -> true
                }
            }
            .let { filteredList ->
                if (isDescending) {
                    filteredList.sortedByDescending { it.startDate }
                } else {
                    filteredList.sortedBy { it.startDate }
                }
            }
    }

    LaunchedEffect(state.selectedTabIndex, selectedFilter) {
        viewModel.onIntent(ProjectsIntent.GetProjectsList)
    }

    LaunchedEffect(isDescending) {
        lazyProjectsListState.scrollToItem(0)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onTertiaryContainer),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
                .align(Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Sort:",
                    color = MaterialTheme.colorScheme.onTertiary,
                    style = MaterialTheme.typography.bodySmall,
                )
                AssistChip(
                    onClick = { isDescending = !isDescending },
                    label = {
                        Text(
                            text = if (isDescending) "ASC" else "DESC",
                            style = MaterialTheme.typography.bodySmall,
                        )
                    },
                    colors = AssistChipDefaults.assistChipColors(
                        containerColor = MaterialTheme.colorScheme.secondary,
                        labelColor = MaterialTheme.colorScheme.onSecondary,
                    ),
                    leadingIcon = {
                        Icon(
                            imageVector = if (isDescending) Icons.Outlined.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                            contentDescription = "Localized description",
                            tint = MaterialTheme.colorScheme.onSecondary,
                            modifier = Modifier.size(AssistChipDefaults.IconSize)
                        )
                    }
                )
            }
            Column(
                modifier = Modifier.weight(3f)
            ) {
                Text(
                    text = "Status:",
                    color = MaterialTheme.colorScheme.onTertiary,
                    style = MaterialTheme.typography.bodySmall,
                )
                ChipsRow(
                    chips = filterOptions,
                    selectedChip = selectedFilter,
                    onFilterSelected = { newFilter -> selectedFilter = newFilter },
                )
            }
        }

        ScreenSurface {
            Column {
                ProjectsTabs(
                    firstTabTitle = "Projects",
                    firstTabContent = {
                        ProjectsList(
                            modifier = Modifier,
                            scrollState = lazyProjectsListState,
                            projectList = filteredAndSortedProjects,
                            isDescending = isDescending
                        )
                    },
                    secondTabTitle = "Discussions",
                    secondTabContent = {
                        Text("No discussions yet")
                    },
                    thirdTabTitle = "Issues",
                    thirdTabContent = {
                        Text("No issues yet")
                    },
                )
            }
        }
    }
}
