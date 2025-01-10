package com.miluconnect.profeliomp.presentation.screens.projects

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.miluconnect.profeliomp.presentation.components.ProjectsList
import com.miluconnect.profeliomp.presentation.components.ScreenSurface
import com.miluconnect.profeliomp.presentation.components.projectsTabs.ProjectsTabs
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
    val lazyProjectsListState = rememberLazyListState()

    LaunchedEffect(state.projectsList) {
        lazyProjectsListState.animateScrollToItem(index = 0)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onTertiaryContainer)
            .statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ScreenSurface {
            Column {
                ProjectsTabs(
                    firstTabTitle = "Projects",
                    firstTabContent = {
                        ProjectsList(
                            modifier = Modifier,
                            scrollState = lazyProjectsListState,
                            projectList = state.projectsList
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