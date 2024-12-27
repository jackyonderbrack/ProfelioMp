package com.miluconnect.profeliomp.data.user.mappers

import com.miluconnect.profeliomp.data.user.dto.UserDto
import com.miluconnect.profeliomp.domain.user.User

fun UserDto.toUser(): User {
    return User(
        id = id,
        name = name,
        email = email,
        isAdmin = isAdmin,
        accountType = accountType,

    )
}