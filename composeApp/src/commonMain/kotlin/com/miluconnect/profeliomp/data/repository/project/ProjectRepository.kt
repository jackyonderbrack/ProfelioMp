package com.miluconnect.profeliomp.data.repository.project

import com.miluconnect.profeliomp.data.mappers.toProjectModel
import com.miluconnect.profeliomp.data.network.RemoteProjectDataSource
import com.miluconnect.profeliomp.domain.core.DataError
import com.miluconnect.profeliomp.domain.core.DataResult
import com.miluconnect.profeliomp.domain.core.map
import com.miluconnect.profeliomp.domain.models.Project

interface ProjectRepository {
    suspend fun getAllProjects(): DataResult<List<Project>, DataError.Remote>
    suspend fun getProjectDetails(id: String): DataResult<Project, DataError.Remote>
    suspend fun createNewProject(newProject: Project): DataResult<Project, DataError.Remote>
}

class ProjectRepositoryImpl(
    private val remoteDataSource: RemoteProjectDataSource
): ProjectRepository {
    override suspend fun getAllProjects(): DataResult<List<Project>, DataError.Remote> {
        return remoteDataSource
            .getAllProjects()
            .map { items -> items.map { it.toProjectModel() } }
    }

    override suspend fun getProjectDetails(id: String): DataResult<Project, DataError.Remote> {
        return remoteDataSource
            .getProjectDetails(id)
            .map { it.toProjectModel() }
    }

    override suspend fun createNewProject(newProject: Project): DataResult<Project, DataError.Remote> {
        return remoteDataSource
            .createNewProject(newProject)
            .map { it.toProjectModel() }
    }
}
