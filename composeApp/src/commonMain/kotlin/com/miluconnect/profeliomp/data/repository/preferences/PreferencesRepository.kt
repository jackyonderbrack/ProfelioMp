package com.miluconnect.profeliomp.data.repository.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface PreferencesRepository {
    suspend fun getToken(): Flow<String?>
    suspend fun saveToken(token: String)
    suspend fun getRefreshToken(): Flow<String?>
    suspend fun saveRefreshToken(refreshToken: String)
}

class PreferencesRepositoryImpl(
    private val dataStore: DataStore<Preferences>
) : PreferencesRepository {

    private object PreferenceKeys {
        val TOKEN = stringPreferencesKey("token")
        val REFRESH_TOKEN = stringPreferencesKey("refresh_token")
    }

    override suspend fun getToken(): Flow<String?> {
        return dataStore.data.map {
            it[PreferenceKeys.TOKEN]
        }
    }

    override suspend fun saveToken(token: String) {
        dataStore.edit {
            it[PreferenceKeys.TOKEN] = token
        }
    }

    override suspend fun getRefreshToken(): Flow<String?> {
        return dataStore.data.map {
            it[PreferenceKeys.REFRESH_TOKEN]
        }
    }

    override suspend fun saveRefreshToken(refreshToken: String) {
        dataStore.edit {
            it[PreferenceKeys.REFRESH_TOKEN] = refreshToken
        }
    }
}