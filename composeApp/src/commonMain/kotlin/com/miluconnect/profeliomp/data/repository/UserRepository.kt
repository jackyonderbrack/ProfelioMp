package com.miluconnect.profeliomp.data.repository

import com.miluconnect.profeliomp.domain.core.DataError
import com.miluconnect.profeliomp.domain.core.Result
import com.miluconnect.profeliomp.domain.models.User

interface UserRepository {
    suspend fun getCurrentUser(): Result<User, DataError.Remote>
}

class UserRepositoryImpl(

): UserRepository {
    override suspend fun getCurrentUser(): Result<User, DataError.Remote> {
        TODO("Not yet implemented")
    }

}
