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
        return listOf(
            Project(
                id = 1.toString(),
                title = "New house in Miami",
                owner = "Mariah Carr",
                startDate = "20.01.2025",
                city = "Miami",
                status = "Ongoing"
            ),
            Project(
                id = 2.toString(),
                title = "Door repair",
                owner = "John Random",
                startDate = "30.11.2025",
                city = "New York",
                status = "Completed"
            ),
            Project(
                id = 3.toString(),
                title = "Office renovation",
                owner = "Anna Smith",
                startDate = "15.03.2025",
                city = "San Francisco",
                status = "Ongoing"
            ),
            Project(
                id = 4.toString(),
                title = "Kitchen remodeling",
                owner = "Robert Brown",
                startDate = "01.07.2025",
                city = "Chicago",
                status = "Archived"
            ),
            Project(
                id = 5.toString(),
                title = "Bathroom upgrade",
                owner = "Emma Wilson",
                startDate = "10.05.2025",
                city = "Houston",
                status = "Completed"
            ),
            Project(
                id = 6.toString(),
                title = "Pool construction",
                owner = "Michael Johnson",
                startDate = "25.08.2025",
                city = "Phoenix",
                status = "Ongoing"
            ),
            Project(
                id = 7.toString(),
                title = "Garage extension",
                owner = "Sarah Davis",
                startDate = "12.02.2025",
                city = "Philadelphia",
                status = "Ongoing"
            ),
            Project(
                id = 8.toString(),
                title = "Roof replacement",
                owner = "David Martinez",
                startDate = "08.09.2025",
                city = "San Diego",
                status = "Archived"
            ),
            Project(
                id = 9.toString(),
                title = "Landscaping project",
                owner = "Sophia Garcia",
                startDate = "18.04.2025",
                city = "Dallas",
                status = "Completed"
            ),
            Project(
                id = 10.toString(),
                title = "Library construction",
                owner = "James White",
                startDate = "05.06.2025",
                city = "San Antonio",
                status = "Ongoing"
            ),
            Project(
                id = 11.toString(),
                title = "Painting exterior walls",
                owner = "Olivia Lee",
                startDate = "20.10.2025",
                city = "Austin",
                status = "Completed"
            ),
            Project(
                id = 12.toString(),
                title = "Basement waterproofing",
                owner = "William Harris",
                startDate = "01.12.2025",
                city = "Seattle",
                status = "Ongoing"
            )
        )
    }

}
