package com.miluconnect.profeliomp.presentation.components.projectsTabs

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun ColumnScope.ProjectsTabs(
    state: ProjectsTabsState,
    firstTabTitle: String,
    secondTabTitle: String,
    firstTabContent: @Composable () -> Unit,
    secondTabContent: @Composable () -> Unit,
) {

    val pagerState = rememberPagerState { 2 }

    TabRow(
        selectedTabIndex = state.selectedTabIndex,
        modifier = Modifier
            .widthIn(max = 700.dp)
            .fillMaxWidth(),
        indicator = { tabPositions ->
            TabRowDefaults.SecondaryIndicator(
                modifier = Modifier.tabIndicatorOffset(tabPositions[0])
            )
        }
    ) {
        Tab(
            selected = state.selectedTabIndex == 0,
            onClick = {},
            modifier = Modifier.weight(1f),
        ) {
            Text(text = firstTabTitle, modifier = Modifier.padding(vertical = 12.dp))
        }
        Tab(
            selected = state.selectedTabIndex == 1,
            onClick = {},
            modifier = Modifier.weight(1f),
        ) {
            Text(text = secondTabTitle, modifier = Modifier.padding(vertical = 12.dp))
        }
    }
    HorizontalPager(
        state = pagerState,
        modifier = Modifier.fillMaxWidth().weight(1f)
    ) { pageIndex: Int ->
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            when (pageIndex) {
                0 -> {
                    if (state.isLoading) {
                        CircularProgressIndicator()
                    } else {
                        state.errorMessage?.let {
                            Text(
                                text = it.asString(),
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.headlineSmall,
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                        firstTabContent()
                    }
                }

                1 -> {
                    if (state.isLoading) {
                        CircularProgressIndicator()
                    } else {
                        state.errorMessage?.let {
                            Text(
                                text = it.asString(),
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.headlineSmall,
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                        secondTabContent()
                    }
                }
            }
        }
    }
}