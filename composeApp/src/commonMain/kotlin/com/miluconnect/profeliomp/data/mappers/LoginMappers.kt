package com.miluconnect.profeliomp.data.mappers

import com.miluconnect.profeliomp.data.dto.LoginDto
import com.miluconnect.profeliomp.domain.models.login.LoginResponse

fun LoginDto.toLoginResponse(): LoginResponse {
    return LoginResponse(
        message = message,
        token = token,
        refreshToken = refreshToken
    )
}