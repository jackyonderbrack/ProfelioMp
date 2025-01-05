package com.miluconnect.profeliomp.domain.core

sealed interface DataError: Error {
    enum class Remote: DataError {
        REQUEST_TIMEOUT,
        TOO_MANY_REQUESTS,
        NOT_FOUND,
        NO_INTERNET,
        UNAUTHORIZED,
        SERVER_ERROR,
        NOT_IMPLEMENTED,
        BAD_GATEWAY,
        SERVICE_UNAVAILABLE,
        GATEWAY_TIMEOUT,
        SERIALIZATION,
        TEMPORARY_REDIRECT,
        UNKNOWN
    }

    enum class Local: DataError {
        DISK_FULL,
        UNKNOWN
    }
}