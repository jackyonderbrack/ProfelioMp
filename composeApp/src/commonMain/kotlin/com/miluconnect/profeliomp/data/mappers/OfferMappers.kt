package com.miluconnect.profeliomp.data.mappers

import com.miluconnect.profeliomp.data.dto.OfferDto
import com.miluconnect.profeliomp.domain.models.Offer

fun OfferDto.toOfferModel(): Offer {
    return Offer(
        id = id,
        title = title,
        category = category,
        city = city,
        imageUrl = imageUrl
    )
}