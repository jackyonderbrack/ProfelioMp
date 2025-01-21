package com.miluconnect.profeliomp.presentation.screens.work.addIssue

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AddIssueScreenRoot(
    viewModel: AddIssueViewModel = koinViewModel<AddIssueViewModel>(),
    navController: NavController
) {

    val state by viewModel.state.collectAsState()

    AddIssueScreen()
}

@Composable
fun AddIssueScreen() {

}