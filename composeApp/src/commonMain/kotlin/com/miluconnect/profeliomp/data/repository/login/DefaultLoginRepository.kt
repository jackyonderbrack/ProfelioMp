package com.miluconnect.profeliomp.data.repository.login

import com.miluconnect.profeliomp.data.mappers.toLoginResponse
import com.miluconnect.profeliomp.data.network.RemoteLoginDataSource
import com.miluconnect.profeliomp.domain.core.DataError
import com.miluconnect.profeliomp.domain.core.Result
import com.miluconnect.profeliomp.domain.core.map
import com.miluconnect.profeliomp.domain.models.LoginPayload
import com.miluconnect.profeliomp.domain.models.LoginResponse

class DefaultLoginRepository(
    private val dataSource: RemoteLoginDataSource
) : LoginRepository {
    override suspend fun login(loginPayload: LoginPayload): Result<LoginResponse, DataError.Remote> {
        return dataSource
            .login(loginPayload)
            .map { it.toLoginResponse() }
    }
}