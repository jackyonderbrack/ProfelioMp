package com.miluconnect.profeliomp.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.miluconnect.profeliomp.domain.models.Offer

@Composable
fun OffersList(offers: List<Offer>) {
    Column {
        offers.forEach { offer ->
            Text(text = "Offer: ${offer.title}, Category: ${offer.category}, City: ${offer.city}")
        }
    }
}