package com.miluconnect.profeliomp.data.network.login

import com.miluconnect.profeliomp.data.core.endpointCall
import com.miluconnect.profeliomp.data.dto.login.LoginDto
import com.miluconnect.profeliomp.domain.core.DataError
import com.miluconnect.profeliomp.domain.core.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.get

private const val BASE_URL = "http://localhost:8080"

interface RemoteLoginDataSource {
    suspend fun login(): Result<LoginDto, DataError.Remote>
}

class RemoteLoginDataSourceImpl (
    private val httpClient: HttpClient
): RemoteLoginDataSource {
    override suspend fun login(): Result<LoginDto, DataError.Remote> {
        return endpointCall {
            httpClient.get(
                urlString = "$BASE_URL/login",
            )
        }
    }
}