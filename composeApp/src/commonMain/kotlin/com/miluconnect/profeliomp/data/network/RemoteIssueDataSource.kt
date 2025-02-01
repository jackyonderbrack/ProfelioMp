package com.miluconnect.profeliomp.data.network

import com.miluconnect.profeliomp.data.core.BASE_URL
import com.miluconnect.profeliomp.data.core.makeRequest
import com.miluconnect.profeliomp.data.dto.IssueDto
import com.miluconnect.profeliomp.data.repository.preferences.PreferencesRepository
import com.miluconnect.profeliomp.domain.core.DataError
import com.miluconnect.profeliomp.domain.core.DataResult
import com.miluconnect.profeliomp.domain.models.Issue
import io.ktor.client.HttpClient
import io.ktor.http.HttpMethod

interface RemoteIssueDataSource {
    suspend fun getAllIssues(): DataResult<List<IssueDto>, DataError.Remote>
    suspend fun createNewIssue(newIssue: Issue): DataResult<IssueDto, DataError.Remote>
}

class RemoteIssuesDataSourceImpl(
    private val httpClient: HttpClient,
    private val preferencesRepository: PreferencesRepository
) : RemoteIssueDataSource {
    override suspend fun getAllIssues(): DataResult<List<IssueDto>, DataError.Remote> {
        return makeRequest(
            requireAuth = true,
            method = HttpMethod.Get,
            httpClient = httpClient,
            preferencesRepository = preferencesRepository,
            url = "$BASE_URL/issues/all"
        )
    }

    override suspend fun createNewIssue(newIssue: Issue): DataResult<IssueDto, DataError.Remote> {
        return makeRequest(
            requireAuth = true,
            method = HttpMethod.Post,
            body = newIssue,
            httpClient = httpClient,
            preferencesRepository = preferencesRepository,
            url = "$BASE_URL/issues/create"
        )
    }

}