package com.miluconnect.profeliomp.presentation.screens.work.screens.project

sealed class ProjectIntent {
    data class GetProject(val id: String) : ProjectIntent()
}