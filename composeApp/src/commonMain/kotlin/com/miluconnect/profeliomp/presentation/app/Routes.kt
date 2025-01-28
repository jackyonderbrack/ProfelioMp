package com.miluconnect.profeliomp.presentation.app

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable

sealed interface Route {
    val route: String
    val title: String
    val icon: ImageVector
    val isDetailScreen: Boolean

    @Serializable
    data object LoginScreen : Route {
        override val route = "login"
        override val title = "Login"
        override val icon: ImageVector = Icons.Default.Lock
        override val isDetailScreen = true
    }

    @Serializable
    data object BlackboardScreen : Route {
        override val route = "blackboard"
        override val title = "Blackboard"
        override val icon: ImageVector = Icons.Default.Search
        override val isDetailScreen = false
    }

    @Serializable
    data object WorkScreen : Route {
        override val route = "work"
        override val title = "Work"
        override val icon: ImageVector = Icons.Default.Build
        override val isDetailScreen = false
    }

    @Serializable
    data object AccountScreen : Route {
        override val route = "account"
        override val title = "Account"
        override val icon: ImageVector = Icons.Default.AccountBox
        override val isDetailScreen = false
    }

    @Serializable
    data object AddProjectScreen : Route {
        override val route = "addNewProjecct"
        override val title = "Add New Project"
        override val icon: ImageVector = Icons.Default.Add
        override val isDetailScreen = true
    }

    @Serializable
    data object AddIssueScreen : Route {
        override val route = "addNewIssue"
        override val title = "Create new issue"
        override val icon: ImageVector = Icons.Default.Add
        override val isDetailScreen = true
    }

    @Serializable
    data object ProjectDetailsScreen : Route {
        override val route = "projectDetails/{projectId}"
        override val title = "Project Details"
        override val icon: ImageVector = Icons.Default.Add
        override val isDetailScreen = true
    }
}

val allRoutes = listOf(
    // Login Screen
    Route.LoginScreen,

    // Main Screens
    Route.BlackboardScreen,
    Route.WorkScreen,
    Route.AccountScreen,

    //Details Screens
    Route.AddProjectScreen,
    Route.AddIssueScreen,
    Route.ProjectDetailsScreen
)
