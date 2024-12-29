package com.miluconnect.profeliomp.presentation.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.miluconnect.profeliomp.domain.models.login.LoginPayload
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LoginScreenRoot(
    viewModel: LoginViewModel = koinViewModel<LoginViewModel>(),
    onLoginClick: (LoginPayload) -> Unit,
    modifier: Modifier = Modifier
) {

    val state by viewModel.state.collectAsState()
    LoginScreen(
        state = state,
        onAction = { intent ->
            when (intent) {
                is LoginIntent.UpdateUsername -> viewModel.onIntent(intent)
                is LoginIntent.UpdatePassword -> viewModel.onIntent(intent)
                is LoginIntent.Login -> {
                    viewModel.onIntent(intent)
                    onLoginClick(intent.loginPayload)
                }
            }
        }
    )
}

@Composable
fun LoginScreen(
    state: LoginState,
    onAction: (LoginIntent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = state.username,
            onValueChange = { text ->
                onAction(LoginIntent.UpdateUsername(text))
            },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = state.password,
            onValueChange = { text ->
                onAction(LoginIntent.UpdatePassword(text))
            },
            visualTransformation = PasswordVisualTransformation(),
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { onAction(LoginIntent.Login(
                LoginPayload(
                username = state.username,
                password = state.password
            )
            )) },
            modifier = Modifier.fillMaxWidth(),
            enabled = !state.isLoading
        ) {
            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.size(24.dp))
            } else {
                Text("Login")
            }
        }
        state.errorMessage?.let {
            Spacer(modifier = Modifier.height(8.dp))
            Text(it, color = MaterialTheme.colorScheme.error)
        }
    }
}
