package com.miluconnect.profeliomp.domain.models

data class Offer(
    val id: String,
    val title: String,
    val category: String,
    val city: String,
    val imageUrl: String?,
)