package com.miluconnect.profeliomp.presentation.screens.projects

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.miluconnect.profeliomp.presentation.app.Route
import com.miluconnect.profeliomp.presentation.components.Button
import com.miluconnect.profeliomp.presentation.components.ButtonType
import com.miluconnect.profeliomp.presentation.components.chipsRow.ChipsRow
import com.miluconnect.profeliomp.presentation.components.ProjectsList
import com.miluconnect.profeliomp.presentation.components.ScreenSurface
import com.miluconnect.profeliomp.presentation.components.projectsTabs.ProjectsTabs
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ProjectsScreenRoot(
    viewModel: ProjectsViewModel = koinViewModel<ProjectsViewModel>(),
    navController: NavController
) {

    val state by viewModel.state.collectAsState()

    ProjectsScreen(
        state = state,
        navController = navController
    )
}

@Composable
private fun ProjectsScreen(
    state: ProjectsState,
    navController: NavController
) {
    val lazyProjectsListState = rememberLazyListState()
    val filterOptions = listOf("Ongoing", "Completed", "Archived", "Urgent", "Draft")
    var selectedFilter by remember { mutableStateOf("Ongoing") }

    val filteredProjects = state.projectsList.filter { project ->
        when (selectedFilter) {
            "Ongoing" -> project.status == "Ongoing"
            "Completed" -> project.status == "Completed"
            "Archived" -> project.status == "Archived"
            else -> true
        }
    }

    LaunchedEffect(filteredProjects) {
        lazyProjectsListState.scrollToItem(0)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onTertiaryContainer),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            Modifier.fillMaxWidth().padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = { navController.navigate(route = Route.AddProjectScreen.route) },
                label = "+ Create project"
            )
            Button(
                onClick = {},
                label = "+ Issue",
                buttonType = ButtonType.TEXT
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
                .align(Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Status:",
                modifier = Modifier.weight(1f).padding(start = 8.dp),
                color = MaterialTheme.colorScheme.onTertiary,
                style = MaterialTheme.typography.bodyLarge
            )
            ChipsRow(
                chips = filterOptions,
                selectedChip = selectedFilter,
                onFilterSelected = { newFilter -> selectedFilter = newFilter },
                modifier = Modifier.weight(5f)
            )
        }

        ScreenSurface {
            Column {
                ProjectsTabs(
                    firstTabTitle = "Projects",
                    firstTabContent = {
                        ProjectsList(
                            modifier = Modifier,
                            scrollState = lazyProjectsListState,
                            projectList = filteredProjects,
                        )
                    },
                    secondTabTitle = "Discussions",
                    secondTabContent = {
                        Text("Second Tab text")
                    },
                )
            }
        }
    }
}
