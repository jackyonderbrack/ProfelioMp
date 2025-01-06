package com.miluconnect.profeliomp.data.repository.user

import com.miluconnect.profeliomp.data.mappers.toUserModel
import com.miluconnect.profeliomp.data.network.RemoteUserDataSource
import com.miluconnect.profeliomp.domain.core.DataError
import com.miluconnect.profeliomp.domain.core.Result
import com.miluconnect.profeliomp.domain.core.map
import com.miluconnect.profeliomp.domain.models.User

class DefaultUserRepository (
    private val dataSource: RemoteUserDataSource
) : UserRepository {
    override suspend fun getCurrentUserData(): Result<User, DataError.Remote> {
        return dataSource
            .getCurrentUserData()
            .map { it.toUserModel() }
    }
}