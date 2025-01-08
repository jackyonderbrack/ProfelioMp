package com.miluconnect.profeliomp.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.miluconnect.profeliomp.domain.models.Offer

@Composable
fun OffersList(offers: List<Offer>) {
    Column {
        offers.forEach { offer ->
            OffersListItem(offer)
        }
    }
}