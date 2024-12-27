package com.miluconnect.profeliomp.data.network

import com.miluconnect.profeliomp.data.core.endpointCall
import com.miluconnect.profeliomp.domain.core.DataError
import com.miluconnect.profeliomp.domain.core.Result
import com.miluconnect.profeliomp.domain.user.User
import io.ktor.client.HttpClient
import io.ktor.client.request.get

private const val BASE_URL = "http://localhost:8080"

class KtorRemoteUserDataSource(
    private val httpClient: HttpClient
) {
    suspend fun getCurrentUser(): Result<User, DataError.Remote> {
        return endpointCall {
            httpClient.get(
                urlString = "$BASE_URL/users/current",

            )
        }
    }
}