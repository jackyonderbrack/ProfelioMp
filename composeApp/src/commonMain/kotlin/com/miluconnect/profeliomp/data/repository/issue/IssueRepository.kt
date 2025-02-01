package com.miluconnect.profeliomp.data.repository.issue

import com.miluconnect.profeliomp.data.mappers.toIssueModel
import com.miluconnect.profeliomp.data.network.RemoteIssueDataSource
import com.miluconnect.profeliomp.domain.core.DataError
import com.miluconnect.profeliomp.domain.core.DataResult
import com.miluconnect.profeliomp.domain.core.map
import com.miluconnect.profeliomp.domain.models.Issue

interface IssueRepository {
    suspend fun getAllIssues(): DataResult<List<Issue>, DataError.Remote>
    suspend fun createNewIssue(newIssue: Issue): DataResult<Issue, DataError.Remote>
}

class IssueRepositoryImpl(
    private val remoteDataSource: RemoteIssueDataSource
) : IssueRepository {
    override suspend fun getAllIssues(): DataResult<List<Issue>, DataError.Remote> {
        return remoteDataSource
            .getAllIssues()
            .map { items -> items.map { it.toIssueModel() } }
    }

    override suspend fun createNewIssue(newIssue: Issue): DataResult<Issue, DataError.Remote> {
        return remoteDataSource
            .createNewIssue(newIssue)
            .map { it.toIssueModel() }
    }

}

