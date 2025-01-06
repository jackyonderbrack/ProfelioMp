package com.miluconnect.profeliomp

import com.miluconnect.profeliomp.domain.models.LoginResponse

sealed class AppIntent {
    data class SetPreferencesTokens(val loginResponse: LoginResponse): AppIntent()
    data object GetPreferencesTokens: AppIntent()
    data object ClearPreferences: AppIntent()
    data object RestoreNavigationState: AppIntent()
}