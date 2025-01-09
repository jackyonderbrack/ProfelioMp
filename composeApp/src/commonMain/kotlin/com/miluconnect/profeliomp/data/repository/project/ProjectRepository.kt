package com.miluconnect.profeliomp.data.repository.project

import com.miluconnect.profeliomp.data.mappers.toProjectModel
import com.miluconnect.profeliomp.data.network.RemoteProjectDataSource
import com.miluconnect.profeliomp.domain.core.DataError
import com.miluconnect.profeliomp.domain.core.Result
import com.miluconnect.profeliomp.domain.core.map
import com.miluconnect.profeliomp.domain.models.Project

interface ProjectRepository {
    suspend fun getAllProjects(): Result<List<Project>, DataError.Remote>
}

class ProjectRepositoryImpl(
    private val remoteDataSource: RemoteProjectDataSource
): ProjectRepository {
    override suspend fun getAllProjects(): Result<List<Project>, DataError.Remote> {
        return remoteDataSource
            .getAllProjects()
            .map { items -> items.map { it.toProjectModel() } }
    }
}
