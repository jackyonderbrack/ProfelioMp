package com.miluconnect.profeliomp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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

@Composable
@Preview
fun App() {
    MaterialTheme {
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = Route.LoginGraph
            ) {
                navigation<Route.LoginGraph>(
                    startDestination = Route.LoginScreen
                ) {
                    composable<Route.LoginScreen> {
                        val viewModel = koinViewModel<LoginViewModel>()

                        LoginScreenRoot(
                            viewModel = viewModel,
                            onLoginClick = {
                                navController.navigate(Route.AccountScreen)
                            }
                        )
                    }
                    composable<Route.AccountScreen> {
                        val viewModel = koinViewModel<AccountViewModel>()
                        AccountScreenRoot(
                            viewModel = viewModel
                        )
                    }
                }
            }
        }
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