package com.miluconnect.profeliomp.data.network

import com.miluconnect.profeliomp.data.core.BASE_URL
import com.miluconnect.profeliomp.data.core.makeRequest
import com.miluconnect.profeliomp.data.dto.MediaDto
import com.miluconnect.profeliomp.data.repository.preferences.PreferencesRepository
import com.miluconnect.profeliomp.domain.core.DataError
import com.miluconnect.profeliomp.domain.core.DataResult
import com.miluconnect.profeliomp.domain.models.Media
import io.ktor.client.HttpClient
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.formData
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.headersOf

interface RemoteMediaDataSource {
    suspend fun uploadMedia(media: Media): DataResult<MediaDto, DataError.Remote>
    suspend fun getMedia(relatedId: String): DataResult<MediaDto, DataError.Remote>
}

class RemoteMediaDataSourceImpl(
    private val httpClient: HttpClient,
    private val preferencesRepository: PreferencesRepository
) : RemoteMediaDataSource {
    override suspend fun uploadMedia(media: Media): DataResult<MediaDto, DataError.Remote> {
        val multipartData = MultiPartFormDataContent(
            formData {
                append(
                    key = "file",
                    value = media.fileBytes,
                    headers = headersOf(
                        HttpHeaders.ContentType to listOf(media.mimeType),
                        HttpHeaders.ContentDisposition to listOf("filename=\"${media.fileName}\"")
                    )
                )
                append("relatedId", media.relatedId)
                append("fileName", media.fileName)
                append("mimeType", media.mimeType)
                append("createdAt", media.createdAt)
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

    override suspend fun getMedia(relatedId: String): DataResult<MediaDto, DataError.Remote> {
        return makeRequest(
            requireAuth = true,
            method = HttpMethod.Get,
            httpClient = httpClient,
            preferencesRepository = preferencesRepository,
            url = "$BASE_URL/media/get/$relatedId"
        )
    }
}