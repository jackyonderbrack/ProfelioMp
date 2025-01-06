package com.miluconnect.profeliomp.data.network

import com.miluconnect.profeliomp.data.core.authorizedEndpointCall
import com.miluconnect.profeliomp.data.core.endpointCall
import com.miluconnect.profeliomp.data.dto.UserDto
import com.miluconnect.profeliomp.data.repository.preferences.PreferencesRepository
import com.miluconnect.profeliomp.domain.core.DataError
import com.miluconnect.profeliomp.domain.core.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.headers
import kotlinx.coroutines.flow.firstOrNull


private const val BASE_URL = "http://10.0.2.2:8080"

interface RemoteUserDataSource {
    suspend fun getCurrentUserData(): Result<UserDto, DataError.Remote>
}

class RemoteUserDataSourceImpl (
    private val httpClient: HttpClient,
    private val preferencesRepository: PreferencesRepository
): RemoteUserDataSource {
    override suspend fun getCurrentUserData(): Result<UserDto, DataError.Remote> {

        return authorizedEndpointCall(preferencesRepository) { headers ->
            httpClient.get(
                urlString = "$BASE_URL/users/current",
                headers
            )
        }
    }
}