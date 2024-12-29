package com.miluconnect.profeliomp.presentation.app

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object LoginGraph: Route

    @Serializable
    data object LoginScreen: Route

    @Serializable
    data object AccountScreen: Route
}