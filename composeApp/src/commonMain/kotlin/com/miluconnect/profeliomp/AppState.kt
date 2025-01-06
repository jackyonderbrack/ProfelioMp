package com.miluconnect.profeliomp

data class AppState(
    val token: String? = null,
    val isInitialized: Boolean = false, 
    val isLoggedIn: Boolean = false,
)