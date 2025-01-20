package com.miluconnect.profeliomp.data.core

import com.miluconnect.profeliomp.data.repository.preferences.PreferencesRepository
import com.miluconnect.profeliomp.domain.core.DataError
import com.miluconnect.profeliomp.domain.core.Result
import io.ktor.client.HttpClient
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpMethod
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.serialization.SerializationException
import kotlin.coroutines.coroutineContext

/**
 * HTTP Endpoint call - Generic, used in with Auth.
 * */
suspend inline fun <reified T> makeRequest(
    httpClient: HttpClient,
    preferencesRepository: PreferencesRepository,
    url: String,
    method: HttpMethod = HttpMethod.Get,
    body: Any? = null,
    headers: Map<String, String> = emptyMap(),
    requireAuth: Boolean = false
): Result<T, DataError.Remote> {
    val token = if (requireAuth) {
        preferencesRepository.getToken().firstOrNull() ?: return Result.Error(DataError.Remote.UNAUTHORIZED)
    } else null

    return try {
        val response = httpClient.request {
            this.url(url)
            this.method = method
            headers.forEach { (key, value) ->
                this.headers.append(key, value)
            }
            if (requireAuth) {
                this.headers.append("Authorization", "Bearer $token")
            }
            body?.let { this.setBody(it) }
        }
        handleResponse(response)
    } catch(e: Exception) {
        e.printStackTrace()
        return Result.Error(DataError.Remote.REQUEST_TIMEOUT)
    } catch(e: SocketTimeoutException) {
        e.printStackTrace()
        return Result.Error(DataError.Remote.REQUEST_TIMEOUT)
    } catch (e: UnresolvedAddressException) {
        e.printStackTrace()
        return Result.Error(DataError.Remote.NO_INTERNET)
    } catch (e: SerializationException) {
        e.printStackTrace()
        return Result.Error(DataError.Remote.SERIALIZATION)
    } catch (e: Exception) {
        e.printStackTrace()
        coroutineContext.ensureActive()
        return Result.Error(DataError.Remote.UNKNOWN)
    }
}

/**
 * HTTP Endpoint response from calls
 * */
suspend inline fun <reified T> handleResponse(response: HttpResponse): Result<T, DataError.Remote> {
    return when (response.status.value) {
        in 200..299 -> {
            try {
                Result.Success(response.body())
            } catch (e: NoTransformationFoundException) {
                Result.Error(DataError.Remote.SERIALIZATION)
            }
        }
        307 -> Result.Error(DataError.Remote.TEMPORARY_REDIRECT)
        400 -> Result.Error(DataError.Remote.BAD_REQUEST)
        401 -> Result.Error(DataError.Remote.UNAUTHORIZED)
        404 -> Result.Error(DataError.Remote.NOT_FOUND)
        408 -> Result.Error(DataError.Remote.REQUEST_TIMEOUT)
        429 -> Result.Error(DataError.Remote.TOO_MANY_REQUESTS)
        500 -> Result.Error(DataError.Remote.BAD_GATEWAY)
        501 -> Result.Error(DataError.Remote.NOT_IMPLEMENTED)
        502 -> Result.Error(DataError.Remote.BAD_GATEWAY)
        503 -> Result.Error(DataError.Remote.SERVICE_UNAVAILABLE)
        504 -> Result.Error(DataError.Remote.GATEWAY_TIMEOUT)
        in 505..599 -> Result.Error(DataError.Remote.SERVER_ERROR)
        else -> Result.Error(DataError.Remote.UNKNOWN)
    }
}

