package com.miluconnect.profeliomp.data.repository.project

import com.miluconnect.profeliomp.data.dto.ProjectDto
import com.miluconnect.profeliomp.domain.core.DataError
import com.miluconnect.profeliomp.domain.core.Result
import com.miluconnect.profeliomp.domain.models.Project

class DefaultProjectRepository : ProjectRepository {
    override suspend fun getAllProjects(): Result<List<Project>, DataError.Remote> {
        return Result.Success(createDefaultProjects())
    }

    override suspend fun getProjectDetails(id: String): Result<Project, DataError.Remote> {
        return Result.Success(createDefaultSingleProject())
    }

    override suspend fun createNewProject(newProject: Project): Result<Project, DataError.Remote> {
        return Result.Success(createDefaultSingleProject())
    }

    // Mocks:
    private fun createDefaultSingleProject(): Project {
        return Project(
            id = "1",
            title = "New house in Miami",
            customer = "Mariah",
            startDate = "20.01.2025",
            endDate = "30.11.2025",
            city = "Miami",
            status = "Ongoing"
            )
    }

    private fun createDefaultProjects(): List<Project> {
        return listOf(
            Project(
                id = "1",
                title = "New house in Miami",
                customer = "Mariah",
                startDate = "20.01.2025",
                endDate = "30.11.2025",
                city = "Miami",
                status = "Ongoing"
            ),
            Project(
                id = "2",
                title = "Door repair",
                customer = "Mariah",
                startDate = "30.11.2025",
                endDate = "30.11.2025",
                city = "New York",
                status = "Completed"
            ),
            Project(
                id = "3",
                title = "Office renovation",
                customer = "Mariah",
                startDate = "15.03.2025",
                endDate = "30.11.2025",
                city = "San Francisco",
                status = "Ongoing"
            ),
            Project(
                id = "4",
                title = "Kitchen remodeling",
                customer = "Mariah",
                startDate = "01.07.2025",
                endDate = "30.11.2025",
                city = "Chicago",
                status = "Archived"
            ),
            Project(
                id = "5",
                title = "Bathroom upgrade",
                customer = "Mariah",
                startDate = "10.05.2025",
                endDate = "30.11.2025",
                city = "Houston",
                status = "Completed"
            ),
            Project(
                id = "6",
                title = "Pool construction",
                customer = "Mariah",
                startDate = "25.08.2025",
                endDate = "30.11.2025",
                city = "Phoenix",
                status = "Ongoing"
            ),
            Project(
                id = "7",
                title = "Garage extension",
                customer = "Mariah",
                startDate = "12.02.2025",
                endDate = "30.11.2025",
                city = "Philadelphia",
                status = "Ongoing"
            ),
            Project(
                id = "8",
                title = "Roof replacement",
                customer = "Mariah",
                startDate = "08.09.2025",
                endDate = "30.11.2025",
                city = "San Diego",
                status = "Archived"
            ),
            Project(
                id = "9",
                title = "Landscaping project",
                customer = "Mariah",
                startDate = "18.04.2025",
                endDate = "30.11.2025",
                city = "Dallas",
                status = "Completed"
            ),
            Project(
                id = "10",
                title = "Library construction",
                customer = "Mariah",
                startDate = "05.06.2025",
                endDate = "30.11.2025",
                city = "San Antonio",
                status = "Ongoing"
            ),
            Project(
                id = "11",
                title = "Painting exterior walls",
                customer = "Mariah",
                startDate = "20.10.2025",
                endDate = "30.11.2025",
                city = "Austin",
                status = "Completed"
            ),
            Project(
                id = "12",
                title = "Basement waterproofing",
                customer = "Mariah",
                startDate = "01.12.2025",
                endDate = "30.11.2025",
                city = "Seattle",
                status = "Ongoing"
            )
        )
    }

}
