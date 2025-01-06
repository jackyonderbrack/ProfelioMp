package com.miluconnect.profeliomp.data.repository.login

import com.miluconnect.profeliomp.data.mappers.toLoginResponse
import com.miluconnect.profeliomp.data.network.RemoteLoginDataSource
import com.miluconnect.profeliomp.data.repository.preferences.PreferencesRepository
import com.miluconnect.profeliomp.domain.core.DataError
import com.miluconnect.profeliomp.domain.core.Result
import com.miluconnect.profeliomp.domain.core.map
import com.miluconnect.profeliomp.domain.core.onError
import com.miluconnect.profeliomp.domain.core.onSuccess
import com.miluconnect.profeliomp.domain.models.LoginPayload
import com.miluconnect.profeliomp.domain.models.LoginResponse

interface LoginRepository {
    suspend fun login(loginPayload: LoginPayload): Result<LoginResponse, DataError.Remote>
}

class LoginRepositoryImpl (
    private val remoteDataSource: RemoteLoginDataSource,
    private val preferencesRepository: PreferencesRepository
): LoginRepository {
    override suspend fun login(loginPayload: LoginPayload): Result<LoginResponse, DataError.Remote> {
        return remoteDataSource
            .login(loginPayload)
            .onSuccess {
                preferencesRepository.saveToken(it.token)
                preferencesRepository.saveRefreshToken(it.refreshToken)
            }
            .onError {
                preferencesRepository.clearPreferences()
            }
            .map { it.toLoginResponse() }
    }
}