package com.miluconnect.profeliomp.presentation.screens.blackboard

import com.miluconnect.profeliomp.domain.models.Offer
import com.miluconnect.profeliomp.presentation.core.UiText

data class BlackboardState(
    val isLoading: Boolean = false,
    val errorMessage: UiText? = null,

    val offersList: List<Offer> = emptyList()
)
