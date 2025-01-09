package com.miluconnect.profeliomp.data.mappers

import com.miluconnect.profeliomp.data.dto.LoginResponseDto
import com.miluconnect.profeliomp.domain.models.LoginResponse

fun LoginResponseDto.toLoginResponse(): LoginResponse {
    return LoginResponse(
        message = message,
        token = token,
        refreshToken = refreshToken
    )
}