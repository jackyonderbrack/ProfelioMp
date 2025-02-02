package com.miluconnect.profeliomp.data.network

import com.miluconnect.profeliomp.data.core.BASE_URL
import com.miluconnect.profeliomp.data.core.makeRequest
import com.miluconnect.profeliomp.data.dto.MediaDto
import com.miluconnect.profeliomp.data.repository.preferences.PreferencesRepository
import com.miluconnect.profeliomp.domain.core.DataError
import com.miluconnect.profeliomp.domain.core.DataResult
import io.ktor.client.HttpClient
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.formData
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod

interface RemoteMediaDataSource {
    suspend fun uploadMedia(image: ByteArray): DataResult<MediaDto, DataError.Remote>
}

class RemoteMediaDataSourceImpl(
    private val httpClient: HttpClient,
    private val preferencesRepository: PreferencesRepository
) : RemoteMediaDataSource {
    override suspend fun uploadMedia(image: ByteArray): DataResult<MediaDto, DataError.Remote> {
        val multipartData = MultiPartFormDataContent(
            formData {
                append("file", image, Headers.build {
                    append(
                        name = HttpHeaders.ContentType,
                        value = "image/jpeg"
                    )
                    append(
                        name = HttpHeaders.ContentDisposition,
                        value = "name=\"file\"; filename=\"image.jpg\""
                    )
                })
            }
        )
        return makeRequest(
            httpClient = httpClient,
            preferencesRepository = preferencesRepository,
            url = "$BASE_URL/media/create",
            method = HttpMethod.Post,
            body = multipartData,
            requireAuth = true
        )
    }
}