package com.miluconnect.profeliomp.data.network

import com.miluconnect.profeliomp.data.core.BASE_URL
import com.miluconnect.profeliomp.data.core.makeRequest
import com.miluconnect.profeliomp.data.dto.ProjectDto
import com.miluconnect.profeliomp.data.repository.preferences.PreferencesRepository
import com.miluconnect.profeliomp.domain.core.DataError
import com.miluconnect.profeliomp.domain.core.DataResult
import com.miluconnect.profeliomp.domain.core.EmptyResult
import com.miluconnect.profeliomp.domain.models.Project
import io.ktor.client.HttpClient
import io.ktor.http.HttpMethod
import kotlinx.serialization.json.Json

interface RemoteProjectDataSource {
    suspend fun getAllProjects(): DataResult<List<ProjectDto>, DataError.Remote>
    suspend fun getProjectDetails(id: String): DataResult<ProjectDto, DataError.Remote>
    suspend fun deleteProject(id: String): EmptyResult<DataError.Remote>
    suspend fun createNewProject(newProject: Project): DataResult<ProjectDto, DataError.Remote>
}

class RemoteProjectDataSourceImpl (
    private val httpClient: HttpClient,
    private val preferencesRepository: PreferencesRepository
): RemoteProjectDataSource {
    override suspend fun getAllProjects(): DataResult<List<ProjectDto>, DataError.Remote> {
        return makeRequest(
            requireAuth = true,
            method = HttpMethod.Get,
            httpClient = httpClient,
            preferencesRepository = preferencesRepository,
            url = "$BASE_URL/projects/all"
        )
    }

    override suspend fun getProjectDetails(id: String): DataResult<ProjectDto, DataError.Remote> {
        return makeRequest(
            requireAuth = true,
            method = HttpMethod.Get,
            httpClient = httpClient,
            preferencesRepository = preferencesRepository,
            url = "$BASE_URL/projects/${id}"
        )
    }

    override suspend fun deleteProject(id: String): EmptyResult<DataError.Remote> {
        return makeRequest(
            requireAuth = true,
            method = HttpMethod.Delete,
            httpClient = httpClient,
            preferencesRepository = preferencesRepository,
            url = "$BASE_URL/projects/${id}"
        )
    }

    override suspend fun createNewProject(newProject: Project): DataResult<ProjectDto, DataError.Remote> {
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