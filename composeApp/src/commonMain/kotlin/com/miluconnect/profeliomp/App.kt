package com.miluconnect.profeliomp

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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


//    val previousRoute = navController.previousBackStackEntry?.destination?.route
//    val navAnimationTransition = if (previousRoute< currentRoute) {
//        AnimatedContentTransitionScope.SlideDirection.Right
//    } else {
//        AnimatedContentTransitionScope.SlideDirection.Left
//    }
//    val navAnimationSpecification = tween<IntOffset>(
//        durationMillis = 200,
//        delayMillis = 100,
//        easing = FastOutSlowInEasing
//    )

    // Purpose: TopBar values
    val topBarTitle = allRoutes.find { it.route == currentRoute }?.title ?: "Profelio"
    val topBarActions: @Composable RowScope.() -> Unit = when (currentRoute) {
        Route.BlackboardScreen.route -> {
            {
                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
                }
            }
        }
        Route.ProjectsScreen.route -> {
            {
                AssistChip(
                    onClick = { navController.navigate(route = Route.AddProjectScreen.route) },
                    colors = AssistChipDefaults.assistChipColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        labelColor = MaterialTheme.colorScheme.onPrimary,
                    ),
                    label = { Text("Project") },
                    leadingIcon = {
                        Icon(
                            Icons.Filled.AddCircle,
                            contentDescription = "Add new project icon",
                            Modifier.size(AssistChipDefaults.IconSize),
                            tint = MaterialTheme.colorScheme.onSecondary,
                        )
                    }
                )
                Spacer(modifier = Modifier.width(8.dp))
                AssistChip(
                    onClick = { navController.navigate(route = Route.AddProjectScreen.route) },
                    colors = AssistChipDefaults.assistChipColors(
                        containerColor = MaterialTheme.colorScheme.tertiary,
                        labelColor = MaterialTheme.colorScheme.onTertiary,
                    ),
                    label = { Text("Issue") },
                    leadingIcon = {
                        Icon(
                            Icons.Filled.AddCircle,
                            contentDescription = "Add new issue icon",
                            Modifier.size(AssistChipDefaults.IconSize),
                            tint = MaterialTheme.colorScheme.onSecondary,
                        )
                    }
                )
            }
        }

        else -> {{}}
    }

    LaunchedEffect(state.token) {
        if (state.token == null) {
            navController.navigate(Route.LoginScreen.route) {
                popUpTo(0) { inclusive = true }
            }
        } else {
            navController.navigate(Route.ProjectsScreen.route)
        }
    }

    /**
     * Actual UI
     */
    MaterialTheme {
        Scaffold(
            containerColor = MaterialTheme.colorScheme.onTertiaryContainer,
            topBar = {
                val canNavigateBack =
                    allRoutes.find { it.route == currentRoute }?.isDetailScreen == true
                            && allRoutes.find { it.route == currentRoute }?.route != "login"

                TopBar(
                    currentNavigationTitle = topBarTitle,
                    canNavigateBack = canNavigateBack,
                    navigateUp = { navController.navigateUp() },
                    actionButtons = topBarActions,
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
                startDestination = Route.ProjectsScreen.route,
                modifier = Modifier.padding(it),
//                enterTransition = { slideIntoContainer(navAnimationTransition, navAnimationSpecification) },
//                exitTransition = { slideOutOfContainer(enterTransition, navAnimationSpecification) },
//                popEnterTransition = { slideIntoContainer(navAnimationTransition, navAnimationSpecification) },
//                popExitTransition = { slideOutOfContainer(forwardTransition, navAnimationSpecification) }
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
                    ProjectsScreenRoot(viewModel = koinViewModel(), navController = navController)
                }
                composable(Route.AccountScreen.route) {
                    AccountScreenRoot(viewModel = koinViewModel())
                }

                /* Details navigation screens*/
                composable(Route.AddProjectScreen.route) {
                    AddProjectScreenRoot(viewModel = koinViewModel(), navController = navController)
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