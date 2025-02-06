package com.miluconnect.profeliomp.presentation.screens.work.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.miluconnect.profeliomp.domain.models.Project

@Composable
fun ProjectsList(
    projectList: List<Project>,
    scrollState: LazyListState = rememberLazyListState(),
    isDescending: Boolean,
    modifier: Modifier,
    onListClick: (String) -> Unit, // create callback from list element click
) {

    val sortedProjectList = remember(projectList, isDescending) {
        if (isDescending) {
            projectList.sortedByDescending { it.startDate }
        } else {
            projectList.sortedBy { it.startDate }
        }
    }

    if (projectList.isNotEmpty()) {
        LazyColumn(
            modifier = modifier.fillMaxWidth(),
            state = scrollState,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(
                items = sortedProjectList,
                key = { it.id!! }
            ) { item ->
                ProjectListItem(
                    projectItem = item,
                    onItemClick = onListClick // pass callback
                )
            }
        }
    } else {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "No projects found. Try creating one.")
        }
    }
}