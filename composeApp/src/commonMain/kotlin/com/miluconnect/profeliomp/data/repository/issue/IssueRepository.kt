package com.miluconnect.profeliomp.data.repository.issue

import com.miluconnect.profeliomp.data.mappers.toIssueModel
import com.miluconnect.profeliomp.data.network.RemoteIssueDataSource
import com.miluconnect.profeliomp.domain.core.DataError
import com.miluconnect.profeliomp.domain.core.Result
import com.miluconnect.profeliomp.domain.core.map
import com.miluconnect.profeliomp.domain.models.Issue

interface IssueRepository {
    suspend fun getAllIssues(): Result<List<Issue>, DataError.Remote>
    suspend fun createNewIssue(newIssue: Issue): Result<Issue, DataError.Remote>
}

class IssueRepositoryImpl(
    private val remoteDataSource: RemoteIssueDataSource
) : IssueRepository {
    override suspend fun getAllIssues(): Result<List<Issue>, DataError.Remote> {
        return remoteDataSource
            .getAllIssues()
            .map { items -> items.map { it.toIssueModel() } }
    }

    override suspend fun createNewIssue(newIssue: Issue): Result<Issue, DataError.Remote> {
        return remoteDataSource
            .createNewIssue(newIssue)
            .map { it.toIssueModel() }
    }

}

