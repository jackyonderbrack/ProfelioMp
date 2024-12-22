package com.miluconnect.profeliomp.data.repository.preferences

import kotlinx.coroutines.flow.Flow

actual class PreferencesRepositoryImpl : PreferencesRepository {
    override fun getUserName(): Flow<String?> {
        TODO("Not yet implemented")
    }

    override suspend fun saveUserName(userName: String) {
        TODO("Not yet implemented")
    }

}