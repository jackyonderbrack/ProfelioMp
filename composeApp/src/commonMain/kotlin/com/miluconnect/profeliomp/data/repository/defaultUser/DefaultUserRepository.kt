package com.miluconnect.profeliomp.data.repository.defaultUser

import com.miluconnect.profeliomp.data.network.RemoteUserDataSource
import com.miluconnect.profeliomp.data.user.mappers.toUser
import com.miluconnect.profeliomp.domain.core.DataError
import com.miluconnect.profeliomp.domain.core.Result
import com.miluconnect.profeliomp.domain.core.map
import com.miluconnect.profeliomp.domain.repository.UserRepository
import com.miluconnect.profeliomp.domain.user.User

class DefaultUserRepository(
    private val remoteUserDataSource: RemoteUserDataSource
): UserRepository {
    override suspend fun getCurrentUser(): Result<User, DataError.Remote> {
        return remoteUserDataSource
            .getCurrentUser()
            .map { it.toUser() }
    }
}