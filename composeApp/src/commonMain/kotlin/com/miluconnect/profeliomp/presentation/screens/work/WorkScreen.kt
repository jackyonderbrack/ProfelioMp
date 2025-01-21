package com.miluconnect.profeliomp.presentation.screens.work

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
import com.miluconnect.profeliomp.presentation.screens.work.components.IssuesList
import com.miluconnect.profeliomp.presentation.screens.work.components.ProjectsList
import com.miluconnect.profeliomp.presentation.screens.work.components.ProjectsTabs
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun WorkScreenRoot(
    viewModel: WorkViewModel = koinViewModel<WorkViewModel>(),
) {

    val state by viewModel.state.collectAsState()

    WorkScreen(
        state = state,
        viewModel = viewModel
    )
}

@Composable
private fun WorkScreen(
    state: WorkState,
    viewModel: WorkViewModel
) {
    val lazyProjectsListState = rememberLazyListState()
    var selectedFilter by remember { mutableStateOf("") }
    val filterOptions =
        when (state.selectedTabIndex) {
            0 -> listOf("Ongoing", "Completed", "Archived", "Urgent", "Draft")
            2 -> listOf("To do", "In progress", "Done")
            else -> emptyList()
        }

    var isDescending by remember { mutableStateOf(false) }

    val filteredAndSortedProjects = remember(state.projectsList, selectedFilter, isDescending) {
        val filtered = if (selectedFilter.isEmpty()) {
            state.projectsList
        } else {
            state.projectsList.filter { project ->
                project.status == selectedFilter
            }
        }
        if (isDescending) {
            filtered.sortedByDescending { it.startDate ?: "" }
        } else {
            filtered.sortedBy { it.endDate }
        }
    }

    val filteredAndSortedIssues = remember(state.issuesList, selectedFilter, isDescending) {
        val filtered = if (selectedFilter.isEmpty()) {
            state.issuesList
        } else {
            state.issuesList.filter { project ->
                project.status == selectedFilter
            }
        }
        if (isDescending) {
            filtered.sortedByDescending { it.createdAt ?: "" }
        } else {
            filtered.sortedBy { it.dueTo }
        }
    }

    LaunchedEffect(state.selectedTabIndex, selectedFilter) {
        viewModel.onIntent(WorkIntent.GetProjectsList)
    }

    LaunchedEffect(isDescending) {
        lazyProjectsListState.scrollToItem(0)
    }

    LaunchedEffect(state.selectedTabIndex) {
        selectedFilter = when (state.selectedTabIndex) {
            0 -> "Ongoing"
            2 -> "To do"
            else -> ""
        }
        viewModel.onIntent(WorkIntent.GetProjectsList)
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
                        IssuesList(
                            modifier = Modifier,
                            scrollState = lazyProjectsListState,
                            issuesList = filteredAndSortedIssues,
                            isDescending = isDescending
                        )
                    },
                )
            }
        }
    }
}



