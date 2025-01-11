package com.miluconnect.profeliomp.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.miluconnect.profeliomp.domain.models.Project

@Composable
fun ProjectsList(
    projectList: List<Project>,
    scrollState: LazyListState = rememberLazyListState(),
    modifier: Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        state = scrollState,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(
            items = projectList,
            key = { it.id!! }
        ) { item ->
            ProjectListItem(projectItem = item)
        }
    }
}