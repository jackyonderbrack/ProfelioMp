package com.miluconnect.profeliomp.data.repository.project

import com.miluconnect.profeliomp.data.dto.UserDto
import com.miluconnect.profeliomp.data.repository.project.ProjectRepository
import com.miluconnect.profeliomp.domain.core.DataError
import com.miluconnect.profeliomp.domain.core.Result
import com.miluconnect.profeliomp.domain.models.Project
import com.miluconnect.profeliomp.domain.models.User

class DefaultProjectRepository : ProjectRepository {
    override suspend fun getAllProjects(): Result<List<Project>, DataError.Remote> {
        return Result.Success(createDefaultProjects())
    }

    private fun createDefaultProjects(): List<Project> {
        return listOf(
            Project(
                id = "1",
                title = "New house in Miami",
                users = listOf(
                    User(id = "3213", name = "mcar@example.com", email = "mcar@example.com")
                    // Możesz dodać więcej użytkowników, np.:
                    // User(id = "1234", name = "jane.doe@example.com", email = "jane.doe@example.com")
                ),
                startDate = "20.01.2025",
                endDate = "30.11.2025",
                city = "Miami",
                status = "Ongoing"
            ),
            Project(
                id = "2",
                title = "Door repair",
                users = listOf(
                    User(id = "4321", name = "John Random", email = "john.random@example.com")
                ),
                startDate = "30.11.2025",
                endDate = "30.11.2025",
                city = "New York",
                status = "Completed"
            ),
            Project(
                id = "3",
                title = "Office renovation",
                users = listOf(
                    User(id = "9876", name = "Anna Smith", email = "anna.smith@example.com")
                ),
                startDate = "15.03.2025",
                endDate = "30.11.2025",
                city = "San Francisco",
                status = "Ongoing"
            ),
            Project(
                id = "4",
                title = "Kitchen remodeling",
                users = listOf(
                    User(id = "6543", name = "Robert Brown", email = "robert.brown@example.com")
                ),
                startDate = "01.07.2025",
                endDate = "30.11.2025",
                city = "Chicago",
                status = "Archived"
            ),
            Project(
                id = "5",
                title = "Bathroom upgrade",
                users = listOf(
                    User(id = "1122", name = "Emma Wilson", email = "emma.wilson@example.com")
                ),
                startDate = "10.05.2025",
                endDate = "30.11.2025",
                city = "Houston",
                status = "Completed"
            ),
            Project(
                id = "6",
                title = "Pool construction",
                users = listOf(
                    User(id = "3344", name = "Michael Johnson", email = "michael.johnson@example.com")
                ),
                startDate = "25.08.2025",
                endDate = "30.11.2025",
                city = "Phoenix",
                status = "Ongoing"
            ),
            Project(
                id = "7",
                title = "Garage extension",
                users = listOf(
                    User(id = "5566", name = "Sarah Davis", email = "sarah.davis@example.com")
                ),
                startDate = "12.02.2025",
                endDate = "30.11.2025",
                city = "Philadelphia",
                status = "Ongoing"
            ),
            Project(
                id = "8",
                title = "Roof replacement",
                users = listOf(
                    User(id = "7788", name = "David Martinez", email = "david.martinez@example.com")
                ),
                startDate = "08.09.2025",
                endDate = "30.11.2025",
                city = "San Diego",
                status = "Archived"
            ),
            Project(
                id = "9",
                title = "Landscaping project",
                users = listOf(
                    User(id = "9900", name = "Sophia Garcia", email = "sophia.garcia@example.com")
                ),
                startDate = "18.04.2025",
                endDate = "30.11.2025",
                city = "Dallas",
                status = "Completed"
            ),
            Project(
                id = "10",
                title = "Library construction",
                users = listOf(
                    User(id = "1010", name = "James White", email = "james.white@example.com")
                ),
                startDate = "05.06.2025",
                endDate = "30.11.2025",
                city = "San Antonio",
                status = "Ongoing"
            ),
            Project(
                id = "11",
                title = "Painting exterior walls",
                users = listOf(
                    User(id = "2020", name = "Olivia Lee", email = "olivia.lee@example.com")
                ),
                startDate = "20.10.2025",
                endDate = "30.11.2025",
                city = "Austin",
                status = "Completed"
            ),
            Project(
                id = "12",
                title = "Basement waterproofing",
                users = listOf(
                    User(id = "3030", name = "William Harris", email = "william.harris@example.com")
                ),
                startDate = "01.12.2025",
                endDate = "30.11.2025",
                city = "Seattle",
                status = "Ongoing"
            )
        )
    }

}
