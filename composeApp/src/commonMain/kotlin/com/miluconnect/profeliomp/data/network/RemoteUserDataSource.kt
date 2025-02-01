package com.miluconnect.profeliomp.data.network

import com.miluconnect.profeliomp.data.core.BASE_URL
import com.miluconnect.profeliomp.data.core.makeRequest
import com.miluconnect.profeliomp.data.dto.UserDto
import com.miluconnect.profeliomp.data.repository.preferences.PreferencesRepository
import com.miluconnect.profeliomp.domain.core.DataError
import com.miluconnect.profeliomp.domain.core.DataResult
import io.ktor.client.HttpClient
import io.ktor.http.HttpMethod

interface RemoteUserDataSource {
    suspend fun getCurrentUserData(): DataResult<UserDto, DataError.Remote>
}

class RemoteUserDataSourceImpl (
    private val httpClient: HttpClient,
    private val preferencesRepository: PreferencesRepository
): RemoteUserDataSource {
    override suspend fun getCurrentUserData(): DataResult<UserDto, DataError.Remote> {
        return makeRequest(
            requireAuth = true,
            method = HttpMethod.Get,
            httpClient = httpClient,
            preferencesRepository = preferencesRepository,
            url = "$BASE_URL/users/current"
        )
    }
}