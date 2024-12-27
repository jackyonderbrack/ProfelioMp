package com.miluconnect.profeliomp.domain.repository

import com.miluconnect.profeliomp.domain.core.DataError
import com.miluconnect.profeliomp.domain.core.Result
import com.miluconnect.profeliomp.domain.user.User

interface UserRepository {
    suspend fun getCurrentUser(): Result<User, DataError.Remote>
}