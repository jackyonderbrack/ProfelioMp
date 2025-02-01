package com.miluconnect.profeliomp.data.repository.offer

import com.miluconnect.profeliomp.data.mappers.toOfferModel
import com.miluconnect.profeliomp.data.network.RemoteOfferDataSource
import com.miluconnect.profeliomp.domain.core.DataError
import com.miluconnect.profeliomp.domain.core.DataResult
import com.miluconnect.profeliomp.domain.core.map
import com.miluconnect.profeliomp.domain.models.Offer

interface OfferRepository {
    suspend fun getAllOffers(): DataResult<List<Offer>, DataError.Remote>
}

class OfferRepositoryImpl(
    private val remoteDataSource: RemoteOfferDataSource
): OfferRepository {
    override suspend fun getAllOffers(): DataResult<List<Offer>, DataError.Remote> {
        return remoteDataSource
            .getAllOffers()
            .map { items -> items.map { it.toOfferModel() } }
    }
}
