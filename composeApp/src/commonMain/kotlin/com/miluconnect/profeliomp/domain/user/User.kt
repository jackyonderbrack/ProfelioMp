package com.miluconnect.profeliomp.domain.user

data class User(
    val id: String,
    val name: String,
    val email: String,
    val accountType: String,
    val isAdmin: Boolean
)
