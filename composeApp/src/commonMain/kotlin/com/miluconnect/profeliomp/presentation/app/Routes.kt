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
}

val allRoutes = listOf(
    // Login Screen
    Route.LoginScreen, // 0

    // Main Screens
    Route.BlackboardScreen, // 1
    Route.WorkScreen, // 2
    Route.AccountScreen, // 3

    //Details Screens
    Route.AddProjectScreen // 4
)

// Current 0 -> Right to 1
// Current 1 -> Left to 0, Right to 2,
// Current 2 -> Left to 3, Right to 1,
// Current 2 -> Left to 4, Right to 2 (back) - analogicznie inne details

