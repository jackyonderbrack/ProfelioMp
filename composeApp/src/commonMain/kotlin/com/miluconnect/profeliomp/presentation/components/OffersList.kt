package com.miluconnect.profeliomp.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.miluconnect.profeliomp.domain.models.Offer

@Composable
fun OffersList(
    offers: List<Offer>,
    onOfferClick: (Offer) -> Unit,
    scrollState: LazyListState = rememberLazyListState(),
    modifier: Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        state = scrollState,
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(
            items = offers,
            key = { it.id },
        ) { offer ->
            val itemModifier = if (offers.indexOf(offer) == 0) {
                Modifier.padding(top = 8.dp)
            } else {
                Modifier
            }
            OffersListItem(
                offer = offer,
                modifier = itemModifier
                    .fillMaxWidth()
                    .widthIn(700.dp)
                    .padding(horizontal = 8.dp),
                onOfferClick = { onOfferClick(offer) }
            )
        }
    }
}