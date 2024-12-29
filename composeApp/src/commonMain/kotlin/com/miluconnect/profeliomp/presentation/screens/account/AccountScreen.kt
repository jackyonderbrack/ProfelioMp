package com.miluconnect.profeliomp.presentation.screens.account

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AccountScreen(
    viewModel: AccountViewModel = koinViewModel<AccountViewModel>(),
) {
    Text("AccountScreen")
}