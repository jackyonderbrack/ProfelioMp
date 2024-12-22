package com.miluconnect.profeliomp.data.repository.preferences

import kotlinx.coroutines.flow.Flow

interface PreferencesRepository {
    fun getUserName(): Flow<String?>
    suspend fun saveUserName(userName: String)
}

expect class PreferencesRepositoryImpl : PreferencesRepository