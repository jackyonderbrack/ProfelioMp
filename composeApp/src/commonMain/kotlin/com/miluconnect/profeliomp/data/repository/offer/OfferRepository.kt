package com.miluconnect.profeliomp.data.repository.offer

import com.miluconnect.profeliomp.data.mappers.toOfferModel
import com.miluconnect.profeliomp.data.network.RemoteOfferDataSource
import com.miluconnect.profeliomp.domain.core.DataError
import com.miluconnect.profeliomp.domain.core.Result
import com.miluconnect.profeliomp.domain.core.map
import com.miluconnect.profeliomp.domain.models.Offer

interface OfferRepository {
    suspend fun getCurrentOfferData(): Result<Offer, DataError.Remote>
}

class OfferRepositoryImpl(
    private val remoteDataSource: RemoteOfferDataSource
): OfferRepository {
    override suspend fun getCurrentOfferData(): Result<Offer, DataError.Remote> {
        return remoteDataSource
            .getAllOffers()
            .map { it.toOfferModel() }
    }

}
