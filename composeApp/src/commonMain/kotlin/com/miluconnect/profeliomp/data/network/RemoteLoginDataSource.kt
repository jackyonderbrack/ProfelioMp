package com.miluconnect.profeliomp.data.network

import com.miluconnect.profeliomp.data.core.BASE_URL
import com.miluconnect.profeliomp.data.core.makeRequest
import com.miluconnect.profeliomp.data.dto.LoginResponseDto
import com.miluconnect.profeliomp.data.repository.preferences.PreferencesRepository
import com.miluconnect.profeliomp.domain.core.DataError
import com.miluconnect.profeliomp.domain.core.Result
import com.miluconnect.profeliomp.domain.models.LoginPayload
import io.ktor.client.HttpClient
import io.ktor.http.HttpMethod

interface RemoteLoginDataSource {
    suspend fun login(loginPayload: LoginPayload): Result<LoginResponseDto, DataError.Remote>
}

class RemoteLoginDataSourceImpl (
    private val httpClient: HttpClient,
    private val preferencesRepository: PreferencesRepository
): RemoteLoginDataSource {
    override suspend fun login(loginPayload: LoginPayload): Result<LoginResponseDto, DataError.Remote> {
        return makeRequest(
            requireAuth = false,
            method = HttpMethod.Post,
            body = loginPayload,
            httpClient = httpClient,
            preferencesRepository = preferencesRepository,
            url = "$BASE_URL/login/"
        )
    }
}