package com.miluconnect.profeliomp.data.repository.offer

import com.miluconnect.profeliomp.domain.core.DataError
import com.miluconnect.profeliomp.domain.core.DataResult
import com.miluconnect.profeliomp.domain.models.Offer

class DefaultOfferRepository : OfferRepository {
    override suspend fun getAllOffers(): DataResult<List<Offer>, DataError.Remote> {
        return DataResult.Success(createDefaultOffers())
    }

    private fun createDefaultOffers(): List<Offer> {
        return List(10) { index ->
            Offer(
                id = (index + 1).toString(),
                title = "Default Offer Title $index",
                category = "Default Category",
                city = "Default City $index",
                imageUrl = null
            )
        }
    }
}
