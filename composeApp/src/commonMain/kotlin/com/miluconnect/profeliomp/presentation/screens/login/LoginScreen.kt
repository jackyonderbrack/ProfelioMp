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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.miluconnect.profeliomp.domain.models.LoginPayload
import com.miluconnect.profeliomp.presentation.app.Route
import org.koin.compose.viewmodel.koinViewModel

/**
 * Composable root for the [LoginScreen]:
 * - Responsible for initializing the [LoginScreen] view and passing the ViewModel state and actions
 *   for handling user interactions.
 * - Uses [koinViewModel] to get the ViewModel instance.
 * - The [onLoginSuccess] function handles external actions, such as navigation after successful login.
 */
@Composable
fun LoginScreenRoot(
    viewModel: LoginViewModel = koinViewModel<LoginViewModel>(),
    onLoginSuccess: () -> Unit,
) {
    /**
     * Retrieves the current state of the ViewModel as an observable object.
     * - Uses [collectAsState] to automatically update the UI when the state changes.
     */
    val state by viewModel.state.collectAsState()

    if (state.token != null) {
        LaunchedEffect(Unit) {
            onLoginSuccess()
        }
    }

    /**
     * Render the [LoginScreen]:
     * - Passes the current state and actions based on user intents.
     */
    LoginScreen(
        state = state,
        onAction = { intent ->
            when (intent) {
                /**
                 * Handle username update:
                 * - Passes the intent of username field change to the ViewModel.
                 */
                is LoginIntent.UpdateUsername -> viewModel.onIntent(intent)

                /**
                 * Handle password update:
                 * - Passes the intent of password field change to the ViewModel.
                 */
                is LoginIntent.UpdatePassword -> viewModel.onIntent(intent)

                /**
                 * Handle login action:
                 * - Passes the intent of button usage to the ViewModel.
                 */
                is LoginIntent.LoginToApp -> {
                    viewModel.onIntent(intent)
                }
            }
        }
    )
}

/**
 * Composable login screen:
 * - Renders the UI for user login inputs.
 * - Handles text fields for username and password, login button, and error messages.
 */
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
        /**
         * Text field for username input:
         * - Updates the value of the field based on user input.
         */
        TextField(
            value = state.username,
            onValueChange = { text ->
                onAction(LoginIntent.UpdateUsername(text))
            },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        /**
         * Text field for password input:
         * - User input is hidden using [PasswordVisualTransformation].
         * - Updates the value of the field based on user input.
         */
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

        /**
         * Login button:
         * - Triggers the login intent on click.
         * - Displays a loader when *isLoading is true.
         */
        Button(
            onClick = {
                onAction(
                    LoginIntent.LoginToApp(
                        LoginPayload(
                            email = state.username,
                            password = state.password
                        )
                    )
                )
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = !state.isLoading
        ) {
            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.size(24.dp))
            } else {
                Text("Login")
            }
        }

        /**
         * Displays an error message if it exists.
         */
        state.errorMessage?.let {
            Spacer(modifier = Modifier.height(8.dp))
            Text(it.asString(), color = MaterialTheme.colorScheme.error)
        }
        state.responseMessage?.let {
            Spacer(modifier = Modifier.height(8.dp))
            Text(it, color = MaterialTheme.colorScheme.onPrimaryContainer)
        }
        state.token?.let {
            Spacer(modifier = Modifier.height(8.dp))
            Text(it, color = MaterialTheme.colorScheme.onPrimaryContainer)
        }
        state.refreshToken?.let {
            Spacer(modifier = Modifier.height(8.dp))
            Text(it, color = MaterialTheme.colorScheme.onPrimaryContainer)
        }
    }
}
