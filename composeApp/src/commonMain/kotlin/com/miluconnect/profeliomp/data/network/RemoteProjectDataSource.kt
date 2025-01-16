package com.miluconnect.profeliomp.data.network

import com.miluconnect.profeliomp.data.core.BASE_URL
import com.miluconnect.profeliomp.data.core.authorizedEndpointCall
import com.miluconnect.profeliomp.data.dto.ProjectDto
import com.miluconnect.profeliomp.data.repository.preferences.PreferencesRepository
import com.miluconnect.profeliomp.domain.core.DataError
import com.miluconnect.profeliomp.domain.core.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.http.headers

interface RemoteProjectDataSource {
    suspend fun getAllProjects(): Result<List<ProjectDto>, DataError.Remote>
    suspend fun createNewProject(): Result<ProjectDto, DataError.Remote>

}

class RemoteProjectDataSourceImpl (
    private val httpClient: HttpClient,
    private val preferencesRepository: PreferencesRepository
): RemoteProjectDataSource {
    override suspend fun getAllProjects(): Result<List<ProjectDto>, DataError.Remote> {

        return authorizedEndpointCall(preferencesRepository) { headers ->
            httpClient.get(
                urlString = "$BASE_URL/projects/all",
                headers
            )
        }
    }

    override suspend fun createNewProject(): Result<ProjectDto, DataError.Remote> {
        return authorizedEndpointCall(preferencesRepository) { headers ->
            httpClient.post(
                urlString = "$BASE_URL/projects/create",
                headers
            )
        }
    }
}