package com.miluconnect.profeliomp.data.mappers.login

import com.miluconnect.profeliomp.data.dto.login.LoginDto
import com.miluconnect.profeliomp.domain.models.login.LoginResponse

fun LoginDto.toLoginResponse(): LoginResponse {
    return LoginResponse(
        message = message,
        token = token,
        refreshToken = refreshToken
    )
}