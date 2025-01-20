package com.miluconnect.profeliomp.data.network

import com.miluconnect.profeliomp.data.core.BASE_URL
import com.miluconnect.profeliomp.data.core.makeRequest
import com.miluconnect.profeliomp.data.dto.ProjectDto
import com.miluconnect.profeliomp.data.repository.preferences.PreferencesRepository
import com.miluconnect.profeliomp.domain.core.DataError
import com.miluconnect.profeliomp.domain.core.Result
import com.miluconnect.profeliomp.domain.models.Project
import io.ktor.client.HttpClient
import io.ktor.http.HttpMethod

interface RemoteProjectDataSource {
    suspend fun getAllProjects(): Result<List<ProjectDto>, DataError.Remote>
    suspend fun createNewProject(newProject: Project): Result<ProjectDto, DataError.Remote>
}

class RemoteProjectDataSourceImpl (
    private val httpClient: HttpClient,
    private val preferencesRepository: PreferencesRepository
): RemoteProjectDataSource {
    override suspend fun getAllProjects(): Result<List<ProjectDto>, DataError.Remote> {
        return makeRequest(
            requireAuth = true,
            method = HttpMethod.Get,
            httpClient = httpClient,
            preferencesRepository = preferencesRepository,
            url = "$BASE_URL/projects/all"
        )
    }

    override suspend fun createNewProject(newProject: Project): Result<ProjectDto, DataError.Remote> {
        return makeRequest(
            requireAuth = true,
            method = HttpMethod.Post,
            body = newProject,
            httpClient = httpClient,
            preferencesRepository = preferencesRepository,
            url = "$BASE_URL/projects/create"
        )
    }
}