package com.miluconnect.profeliomp.data.repository.login

import com.miluconnect.profeliomp.data.mappers.login.toLoginResponse
import com.miluconnect.profeliomp.data.network.login.RemoteLoginDataSource
import com.miluconnect.profeliomp.domain.core.DataError
import com.miluconnect.profeliomp.domain.core.Result
import com.miluconnect.profeliomp.domain.core.map
import com.miluconnect.profeliomp.domain.models.login.LoginPayload
import com.miluconnect.profeliomp.domain.models.login.LoginResponse

interface LoginRepository {
    suspend fun login(loginPayload: LoginPayload): Result<LoginResponse, DataError.Remote>
}

class LoginRepositoryImpl (
    private val remoteLoginDataSource: RemoteLoginDataSource
): LoginRepository {
    override suspend fun login(loginPayload: LoginPayload): Result<LoginResponse, DataError.Remote> {
        return remoteLoginDataSource
            .login()
            .map { it.toLoginResponse() }
    }
}