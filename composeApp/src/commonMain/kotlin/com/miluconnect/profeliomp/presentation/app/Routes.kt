package com.miluconnect.profeliomp.presentation.app

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object AppGraph: Route

    @Serializable
    data object LoginScreen: Route

    @Serializable
    data object AccountScreen: Route

    @Serializable
    data object BlackboardScreen: Route
}