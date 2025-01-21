package com.miluconnect.profeliomp.presentation.screens.work.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.miluconnect.profeliomp.domain.models.Issue

@Composable
fun IssuesList(
    issuesList: List<Issue>,
    scrollState: LazyListState = rememberLazyListState(),
    isDescending: Boolean,
    modifier: Modifier
) {

    val sortedProjectList = remember(issuesList, isDescending) {
        if (isDescending) {
            issuesList.sortedByDescending { it.createdAt }
        } else {
            issuesList.sortedBy { it.dueTo }
        }
    }

    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        state = scrollState,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(
            items = sortedProjectList,
            key = { it.id!! }
        ) { item ->
            IssuesListItem(issueItem = item)
        }
    }
}