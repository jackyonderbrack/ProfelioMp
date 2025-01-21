package com.miluconnect.profeliomp

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
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

    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    val currentRouteIndex = allRoutes.indexOfFirst { it.route == currentRoute }

    var animationDirection by remember { mutableStateOf<AnimatedContentTransitionScope.SlideDirection?>(null) }
    var lastRouteIndex by remember { mutableStateOf(-1) }

    LaunchedEffect(currentRouteIndex) {
        if (currentRouteIndex != -1) {
            animationDirection = when {
                lastRouteIndex == -1 -> AnimatedContentTransitionScope.SlideDirection.Start
                currentRouteIndex > lastRouteIndex -> AnimatedContentTransitionScope.SlideDirection.Left
                currentRouteIndex < lastRouteIndex -> AnimatedContentTransitionScope.SlideDirection.Right
                else -> null
            }
            lastRouteIndex = currentRouteIndex
        }
    }

    val navAnimationSpecification = tween<IntOffset>(
        durationMillis = 200,
        delayMillis = 100,
        easing = FastOutSlowInEasing
    )

    LaunchedEffect(currentRoute, lastRouteIndex) {
        println("NAV Current route: $currentRoute")
        println("NAV Previous route: $lastRouteIndex")
        println("NAV Animation direction: $animationDirection")
    }

    /**
     * TopBar Values
     * */
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

    /**
     * Launched Effects
     * */
//    LaunchedEffect(state.token) {
//        if (state.token == null) {
//            navController.navigate(Route.LoginScreen.route) {
//                popUpTo(0)
//            }
//        } else {
//            navController.navigate(Route.ProjectsScreen.route)
//        }
//    }

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
        ) { paddingValues ->
            NavHost(
                navController = navController,
                startDestination = Route.ProjectsScreen.route,
                modifier = Modifier.padding(paddingValues),
                enterTransition = {
                    animationDirection?.let { direction ->
                        slideIntoContainer(direction, navAnimationSpecification)
                    } ?: EnterTransition.None
                },
                exitTransition = {
                    animationDirection?.let { direction ->
                        slideOutOfContainer(direction, navAnimationSpecification)
                    } ?: ExitTransition.None
                },
                popEnterTransition = {
                    animationDirection?.let { direction ->
                        slideIntoContainer(direction, navAnimationSpecification)
                    } ?: EnterTransition.None
                },
                popExitTransition = {
                    animationDirection?.let { direction ->
                        slideOutOfContainer(direction, navAnimationSpecification)
                    } ?: ExitTransition.None
                }

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
                    ProjectsScreenRoot(viewModel = koinViewModel())
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

