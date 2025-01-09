package com.miluconnect.profeliomp.data.repository.project

import com.miluconnect.profeliomp.data.repository.project.ProjectRepository
import com.miluconnect.profeliomp.domain.core.DataError
import com.miluconnect.profeliomp.domain.core.Result
import com.miluconnect.profeliomp.domain.models.Project

class DefaultProjectRepository : ProjectRepository {
    override suspend fun getAllProjects(): Result<List<Project>, DataError.Remote> {
        return Result.Success(createDefaultProjects())
    }

    private fun createDefaultProjects(): List<Project> {
        return List(10) { index ->
            Project(
                id = (index + 1).toString(),
                title = "Default Project Title $index",
                startDate = "20.01.2025",
                city = "Default City $index",
                status = "In progress"
            )
        }
    }
}
