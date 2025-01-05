package com.miluconnect.profeliomp.data.repository.login

import com.miluconnect.profeliomp.data.mappers.toLoginResponse
import com.miluconnect.profeliomp.data.network.RemoteLoginDataSource
import com.miluconnect.profeliomp.domain.core.DataError
import com.miluconnect.profeliomp.domain.core.Result
import com.miluconnect.profeliomp.domain.core.map
import com.miluconnect.profeliomp.domain.models.LoginPayload
import com.miluconnect.profeliomp.domain.models.LoginResponse

interface LoginRepository {
    suspend fun login(loginPayload: LoginPayload): Result<LoginResponse, DataError.Remote>
}

class LoginRepositoryImpl (
    private val remoteDataSource: RemoteLoginDataSource
): LoginRepository {
    override suspend fun login(loginPayload: LoginPayload): Result<LoginResponse, DataError.Remote> {
        return remoteDataSource
            .login(loginPayload)
            .map { it.toLoginResponse() }
    }
}