package com.miluconnect.profeliomp.presentation.app

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable

sealed interface Route {
    val route: String
    val title: String
    val icon: ImageVector

    @Serializable
    data object LoginScreen : Route {
        override val route = "login"
        override val title = "Login"
        override val icon: ImageVector = Icons.Default.Lock
    }

    @Serializable
    data object BlackboardScreen : Route {
        override val route = "blackboard"
        override val title = "Blackboard"
        override val icon: ImageVector = Icons.Default.Search
    }

    @Serializable
    data object AccountScreen : Route {
        override val route = "account"
        override val title = "Account"
        override val icon: ImageVector = Icons.Default.AccountBox
    }

    @Serializable
    data object ProjectsScreen : Route {
        override val route = "projects"
        override val title = "Projects"
        override val icon: ImageVector = Icons.Default.Build
    }
}
