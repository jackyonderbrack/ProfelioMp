package com.miluconnect.profeliomp.presentation.core

import com.miluconnect.profeliomp.domain.core.DataError
import profeliomp.composeapp.generated.resources.Res
import profeliomp.composeapp.generated.resources.error_bad_gateway
import profeliomp.composeapp.generated.resources.error_disk_full
import profeliomp.composeapp.generated.resources.error_gateway_timeout
import profeliomp.composeapp.generated.resources.error_no_internet
import profeliomp.composeapp.generated.resources.error_not_found
import profeliomp.composeapp.generated.resources.error_not_implemented
import profeliomp.composeapp.generated.resources.error_request_timeout
import profeliomp.composeapp.generated.resources.error_serialization
import profeliomp.composeapp.generated.resources.error_server_error
import profeliomp.composeapp.generated.resources.error_service_unavailable
import profeliomp.composeapp.generated.resources.error_too_many_requests
import profeliomp.composeapp.generated.resources.error_unauthorized
import profeliomp.composeapp.generated.resources.error_unknown_local
import profeliomp.composeapp.generated.resources.error_unknown_remote
import profeliomp.composeapp.generated.resources.error_temporary_redirect

fun DataError.toUiText(): UiText {
    val stringRes = when (this) {
        DataError.Local.DISK_FULL -> Res.string.error_disk_full
        DataError.Local.UNKNOWN -> Res.string.error_unknown_local
        DataError.Remote.REQUEST_TIMEOUT -> Res.string.error_request_timeout
        DataError.Remote.TOO_MANY_REQUESTS -> Res.string.error_too_many_requests
        DataError.Remote.NOT_FOUND -> Res.string.error_not_found
        DataError.Remote.NO_INTERNET -> Res.string.error_no_internet
        DataError.Remote.UNAUTHORIZED -> Res.string.error_unauthorized
        DataError.Remote.SERVER_ERROR -> Res.string.error_server_error
        DataError.Remote.NOT_IMPLEMENTED -> Res.string.error_not_implemented
        DataError.Remote.BAD_GATEWAY -> Res.string.error_bad_gateway
        DataError.Remote.SERVICE_UNAVAILABLE -> Res.string.error_service_unavailable
        DataError.Remote.GATEWAY_TIMEOUT -> Res.string.error_gateway_timeout
        DataError.Remote.SERIALIZATION -> Res.string.error_serialization
        DataError.Remote.TEMPORARY_REDIRECT -> Res.string.error_temporary_redirect
        DataError.Remote.UNKNOWN -> Res.string.error_unknown_remote
    }

    return UiText.StringResourceId(stringRes)
}