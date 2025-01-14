package com.miluconnect.profeliomp.data.mappers

import com.miluconnect.profeliomp.data.dto.UserDto
import com.miluconnect.profeliomp.domain.models.User

fun UserDto.toUserModel(): User {
    return User(
        id = id,
        name = name,
        email = email,
        role = role
    )
}