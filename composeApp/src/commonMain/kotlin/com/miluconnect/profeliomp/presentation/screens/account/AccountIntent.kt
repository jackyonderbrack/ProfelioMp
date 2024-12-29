package com.miluconnect.profeliomp.presentation.screens.account

sealed class AccountIntent {
    data object GetCurrentUser : AccountIntent()
}