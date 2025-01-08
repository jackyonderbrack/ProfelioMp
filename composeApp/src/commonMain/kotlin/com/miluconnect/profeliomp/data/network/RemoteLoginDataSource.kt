package com.miluconnect.profeliomp.data.network

import com.miluconnect.profeliomp.data.core.BASE_URL
import com.miluconnect.profeliomp.data.core.endpointCall
import com.miluconnect.profeliomp.data.dto.LoginResponseDto
import com.miluconnect.profeliomp.domain.core.DataError
import com.miluconnect.profeliomp.domain.core.Result
import com.miluconnect.profeliomp.domain.models.LoginPayload
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

interface RemoteLoginDataSource {
    suspend fun login(loginPayload: LoginPayload): Result<LoginResponseDto, DataError.Remote>
}

class RemoteLoginDataSourceImpl (
    private val httpClient: HttpClient
): RemoteLoginDataSource {
    override suspend fun login(loginPayload: LoginPayload): Result<LoginResponseDto, DataError.Remote> {
        return endpointCall {
            httpClient.post(
                urlString = "$BASE_URL/login/",
            ) {
                contentType(ContentType.Application.Json)
                setBody(loginPayload)
            }.body()
        }
    }
}