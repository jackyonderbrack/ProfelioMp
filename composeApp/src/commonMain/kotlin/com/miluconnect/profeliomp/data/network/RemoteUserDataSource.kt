package com.miluconnect.profeliomp.data.network

import com.miluconnect.profeliomp.data.core.endpointCall
import com.miluconnect.profeliomp.data.user.dto.UserDto
import com.miluconnect.profeliomp.domain.core.DataError
import com.miluconnect.profeliomp.domain.core.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.get

interface RemoteUserDataSource {
    suspend fun getCurrentUser(): Result<UserDto, DataError.Remote>
}

private const val BASE_URL = "http://localhost:8080"

class RemoteUserDataSourceImpl (
    private val httpClient: HttpClient
): RemoteUserDataSource {
    override suspend fun getCurrentUser(): Result<UserDto, DataError.Remote> {
        return endpointCall {
            httpClient.get(
                urlString = "$BASE_URL/users/current",

            )
        }
    }
}

