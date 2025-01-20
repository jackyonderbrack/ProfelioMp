package com.miluconnect.profeliomp.presentation.screens.projects.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.miluconnect.profeliomp.presentation.screens.projects.ProjectsIntent
import com.miluconnect.profeliomp.presentation.screens.projects.ProjectsViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ColumnScope.ProjectsTabs(
    viewModel: ProjectsViewModel = koinViewModel<ProjectsViewModel>(),
    firstTabTitle: String,
    secondTabTitle: String,
    thirdTabTitle: String,
    firstTabContent: @Composable () -> Unit,
    secondTabContent: @Composable () -> Unit,
    thirdTabContent: @Composable () -> Unit,
) {

    val state = viewModel.state.collectAsState()
    val pagerState = rememberPagerState { 3 }

    LaunchedEffect(state.value.selectedTabIndex) {
        println("Selected Tab Index: ${state.value.selectedTabIndex}")
        pagerState.scrollToPage(state.value.selectedTabIndex)
    }

    LaunchedEffect(pagerState.currentPage) {
        println("Current Page: ${pagerState.currentPage}")
        viewModel.onIntent(ProjectsIntent.OnTabSelectedChange(pagerState.currentPage))
    }

    TabRow(
        selectedTabIndex = state.value.selectedTabIndex,
        modifier = Modifier
            .widthIn(max = 700.dp)
            .fillMaxWidth(),
        indicator = { tabPositions ->
            TabRowDefaults.SecondaryIndicator(
                modifier = Modifier.tabIndicatorOffset(tabPositions[state.value.selectedTabIndex])
            )
        },
        tabs = {
            Tab(
                selected = state.value.selectedTabIndex == 0,
                onClick = { viewModel.onIntent(ProjectsIntent.OnTabSelectedChange(0)) },
                modifier = Modifier.weight(1f),
            ) {
                Text(text = firstTabTitle, modifier = Modifier.padding(vertical = 12.dp))
            }
            Tab(
                selected = state.value.selectedTabIndex == 1,
                onClick = { viewModel.onIntent(ProjectsIntent.OnTabSelectedChange(1)) },
                modifier = Modifier.weight(1f),
            ) {
                Text(text = secondTabTitle, modifier = Modifier.padding(vertical = 12.dp))
            }
            Tab(
                selected = state.value.selectedTabIndex == 2,
                onClick = { viewModel.onIntent(ProjectsIntent.OnTabSelectedChange(2)) },
                modifier = Modifier.weight(1f),
            ) {
                Text(text = thirdTabTitle, modifier = Modifier.padding(vertical = 12.dp))
            }
        }
    )
    HorizontalPager(
        state = pagerState,
        modifier = Modifier.fillMaxWidth().weight(1f)
    ) { pageIndex: Int ->
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter,
        ) {
            if (state.value.isLoading) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator(modifier = Modifier.size(128.dp))
                }
            } else {
                state.value.errorMessage?.let {
                    Text(
                        text = it.asString(),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.error
                    )
                }

                when (pageIndex) {
                    0 -> firstTabContent()
                    1 -> secondTabContent()
                    2 -> thirdTabContent()
                }
            }
        }
    }
}