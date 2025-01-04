package com.miluconnect.profeliomp.data.network

import com.miluconnect.profeliomp.data.core.endpointCall
import com.miluconnect.profeliomp.data.dto.UserDto
import com.miluconnect.profeliomp.domain.core.DataError
import com.miluconnect.profeliomp.domain.core.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.get

private const val BASE_URL = "http://10.0.2.2:8080"

interface RemoteUserDataSource {
    suspend fun getCurrentUser(): Result<UserDto, DataError.Remote>
}

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