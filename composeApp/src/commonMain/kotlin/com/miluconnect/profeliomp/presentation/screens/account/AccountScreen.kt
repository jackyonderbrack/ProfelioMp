package com.miluconnect.profeliomp.presentation.screens.account

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AccountScreenRoot(
    viewModel: AccountViewModel = koinViewModel<AccountViewModel>(),
) {

    val vmState by viewModel.state.collectAsState()
    AccountScreen(
        state = vmState
    )
}

@Composable
fun AccountScreen(
    state: AccountState
) {

    Text("Name: ")
    Text("AccountScreen")
    Text("AccountScreen")
}

