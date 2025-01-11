package com.miluconnect.profeliomp

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.miluconnect.profeliomp.presentation.app.Route
import com.miluconnect.profeliomp.presentation.app.allRoutes
import com.miluconnect.profeliomp.presentation.components.BottomNavigationBar
import com.miluconnect.profeliomp.presentation.components.TopBar
import com.miluconnect.profeliomp.presentation.screens.account.AccountScreenRoot
import com.miluconnect.profeliomp.presentation.screens.blackboard.BlackboardScreenRoot
import com.miluconnect.profeliomp.presentation.screens.login.LoginScreenRoot
import com.miluconnect.profeliomp.presentation.screens.projects.ProjectsScreenRoot
import com.miluconnect.profeliomp.presentation.screens.projects.addProject.AddProjectScreenRoot
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

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

    /**
     * Creation of [Route] need to be managed by navController state.
     */
    val navController = rememberNavController()

    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route
    val currentNavigationTitle = allRoutes.find { it.route == currentRoute }?.title ?: "Profelio"

    LaunchedEffect(state.token) {
        if (state.token == null) {
            navController.navigate(Route.LoginScreen.route) {
                popUpTo(0) { inclusive = true }
            }
        } else {
            navController.navigate(Route.BlackboardScreen.route) {
                popUpTo(Route.LoginScreen.route) { inclusive = true }
            }
        }
    }

    /**
     * Actual UI
     */
    MaterialTheme {
        Scaffold(
            containerColor = MaterialTheme.colorScheme.onTertiaryContainer,
            topBar = {
                val canNavigateBack = allRoutes.find { it.route == currentRoute }?.isDetailScreen == true
                TopBar(
                    currentNavigationTitle = currentNavigationTitle,
                    canNavigateBack = canNavigateBack,
                    navigateUp = { navController.navigateUp() }
                )
            },
            bottomBar = {
                if (allRoutes.find { it.route == currentRoute }?.isDetailScreen == false) {
                    BottomNavigationBar(navController = navController)
                }
            }
        ) {
            NavHost(
                navController = navController,
                startDestination = Route.BlackboardScreen.route,
                modifier = Modifier.padding(it),
            ) {

                /* Login screen only */
                composable(Route.LoginScreen.route) {
                    LoginScreenRoot(viewModel = koinViewModel())
                }

                /* Main Bottom navigation screen */
                composable(Route.BlackboardScreen.route) {
                    BlackboardScreenRoot(viewModel = koinViewModel())
                }
                composable(Route.ProjectsScreen.route) {
                    ProjectsScreenRoot(
                        viewModel = koinViewModel(),
                        navController = navController
                    )
                }
                composable(Route.AccountScreen.route) {
                    AccountScreenRoot(viewModel = koinViewModel())
                }

                /* Details navigation screens*/
                composable(Route.AddProjectScreen.route) {
                    AddProjectScreenRoot(viewModel = koinViewModel())
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