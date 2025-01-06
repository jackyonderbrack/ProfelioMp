package com.miluconnect.profeliomp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.miluconnect.profeliomp.presentation.app.Route
import com.miluconnect.profeliomp.presentation.screens.account.AccountScreenRoot
import com.miluconnect.profeliomp.presentation.screens.account.AccountViewModel
import com.miluconnect.profeliomp.presentation.screens.login.LoginScreenRoot
import com.miluconnect.profeliomp.presentation.screens.login.LoginViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun App(
    viewModel: AppViewModel = koinViewModel<AppViewModel>()
) {
    /**
     * Retrieves the current state of the ViewModel as an observable object.
     * - Uses [collectAsState] to automatically update the UI when the state changes.
     */
    val state by viewModel.state.collectAsState()
    val navController = rememberNavController()

    LaunchedEffect(state.token) {
        if (state.token == null) {
            navController.navigate(Route.LoginScreen) {
                popUpTo(0) // clear so user cannot go to loginScreen by clicking "back"
            }
        }
    }

    MaterialTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    colors = topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    title = {
                        Text("Profelio Multiplatform")
                    }
                )
            },
        ) {
            innerPadding ->
            Column(
                Modifier.fillMaxWidth().padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                NavHost(
                    navController = navController,
                    startDestination = Route.AppGraph
                ) {
                    navigation<Route.AppGraph>(
                        startDestination = Route.AccountScreen
                    ) {

                        composable<Route.LoginScreen> {
                            val loginViewModel = koinViewModel<LoginViewModel>()

                            LoginScreenRoot(
                                viewModel = loginViewModel,
                                onLoginSuccess = {
                                    navController.navigate(Route.AccountScreen) {
                                        popUpTo(Route.LoginScreen) { inclusive = true }
                                    }
                                }
                            )
                        }

                        composable<Route.AccountScreen> {
                            val accountViewModel = koinViewModel<AccountViewModel>()

                            AccountScreenRoot(
                                viewModel = accountViewModel
                            )
                        }

                    }
                }
            } // Column
        } // Scaffold
    }
}

@Composable
private inline fun <reified T: ViewModel> NavBackStackEntry.sharedKoinViewModel(
    navController: NavController
): T {
    val navGraphRoute = destination.parent?.route ?: return koinViewModel<T>()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return koinViewModel(viewModelStoreOwner = parentEntry)
}