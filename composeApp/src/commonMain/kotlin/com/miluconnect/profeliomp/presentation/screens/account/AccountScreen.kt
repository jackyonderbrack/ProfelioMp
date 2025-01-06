package com.miluconnect.profeliomp.presentation.screens.account

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AccountScreenRoot(
    viewModel: AccountViewModel = koinViewModel<AccountViewModel>(),
) {
    val state by viewModel.state.collectAsState()

    AccountScreen(
        state = state,
        onAction = { intent ->
            when(intent) {
                AccountIntent.GetCurrentUser -> {}
                AccountIntent.Logout -> {
                    viewModel.onIntent(intent)
                }
            }
        }
    )
}

@Composable
fun AccountScreen(
    state: AccountState,
    onAction: (AccountIntent) -> Unit
) {
    Column {

        OutlinedCard(
            Modifier.fillMaxWidth().padding(16.dp),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
            elevation = CardDefaults.cardElevation(4.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
            ),
        ) {
            IconButton(
                content = {
                    Icon(
                        Icons.Default.AccountBox,
                        contentDescription = "Account",
                        Modifier.size(32.dp)
                    )
                },
                onClick = {}
            )
            Column(
                Modifier.fillMaxWidth().padding(24.dp)
            ) {
                Text(
                    text = "Logged user info",
                    style = MaterialTheme.typography.headlineMedium
                )
                Text(
                    text = "Check all your data",
                )
                Spacer(Modifier.height(16.dp))
                Text("Name: ")
                Spacer(Modifier.height(8.dp))
                Text("Id: ")
                Spacer(Modifier.height(8.dp))
                Text("Email: ")
                Spacer(Modifier.height(8.dp))
                Text("Role: ")
                Spacer(Modifier.height(8.dp))
                AssistChip(
                    onClick = {
                        onAction(
                            AccountIntent.Logout
                        )
                    },
                    label = { Text("Wyloguj") },
                    leadingIcon = {
                        Icon(
                            Icons.Filled.Settings,
                            contentDescription = "Localized description",
                            Modifier.size(AssistChipDefaults.IconSize)
                        )
                    }
                )
            }

        }
    }

}

