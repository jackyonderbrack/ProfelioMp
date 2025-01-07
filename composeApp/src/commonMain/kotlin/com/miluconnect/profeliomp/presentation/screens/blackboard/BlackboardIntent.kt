package com.miluconnect.profeliomp.presentation.screens.blackboard

sealed class BlackboardIntent {
    data object GetOffers : BlackboardIntent()
    data class SelectOffer(val offerId: String) : BlackboardIntent()
}