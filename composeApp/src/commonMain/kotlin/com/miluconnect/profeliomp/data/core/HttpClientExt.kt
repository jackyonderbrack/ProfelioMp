package com.miluconnect.profeliomp.data.core

import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.ensureActive
import com.miluconnect.profeliomp.domain.core.DataError
import com.miluconnect.profeliomp.domain.core.Result
import kotlin.coroutines.coroutineContext

suspend inline fun <reified T> endpointCall(
    execute: () -> HttpResponse
): Result<T, DataError.Remote> {
    val response = try {
        execute()
    } catch(e: SocketTimeoutException) {
        return Result.Error(DataError.Remote.REQUEST_TIMEOUT)
    } catch (e: UnresolvedAddressException) {
        return Result.Error(DataError.Remote.NO_INTERNET)
    } catch (e: Exception) {
        coroutineContext.ensureActive()
        return Result.Error(DataError.Remote.UNKNOWN)
    }

    return endpointResponse(response)
}

suspend inline fun <reified  T> endpointResponse(
    response: HttpResponse
): Result<T, DataError.Remote> {
    return when(response.status.value) {
        in 200 .. 299 -> {
            try {
                Result.Success(response.body<T>())
            } catch (e: NoTransformationFoundException) {
                Result.Error(DataError.Remote.SERIALIZATION)
            }
        }
        301 -> Result.Error(DataError.Remote.UNAUTHORIZED)
        404 -> Result.Error(DataError.Remote.NOT_FOUND)
        408 -> Result.Error(DataError.Remote.REQUEST_TIMEOUT)
        429 -> Result.Error(DataError.Remote.TOO_MANY_REQUESTS)
        500 -> Result.Error(DataError.Remote.BAD_GATEWAY)
        501 -> Result.Error(DataError.Remote.NOT_IMPLEMENTED)
        502 -> Result.Error(DataError.Remote.BAD_GATEWAY)
        503 -> Result.Error(DataError.Remote.SERVICE_UNAVAILABLE)
        504 -> Result.Error(DataError.Remote.GATEWAY_TIMEOUT)
        in 505..599 -> Result.Error(DataError.Remote.SERVER_ERROR)
        else-> Result.Error(DataError.Remote.UNKNOWN)
    }
}