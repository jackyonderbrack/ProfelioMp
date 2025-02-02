package com.miluconnect.profeliomp.data.repository.media

import com.miluconnect.profeliomp.data.mappers.toMediaModel
import com.miluconnect.profeliomp.data.network.RemoteMediaDataSource
import com.miluconnect.profeliomp.domain.core.DataError
import com.miluconnect.profeliomp.domain.core.DataResult
import com.miluconnect.profeliomp.domain.core.map
import com.miluconnect.profeliomp.domain.models.Media

interface MediaRepository {
    suspend fun uploadImage(image: ByteArray): DataResult<Media, DataError.Remote>
}

class MediaRepositoryImpl (
    private val remoteDataSource: RemoteMediaDataSource
) : MediaRepository {
    override suspend fun uploadImage(image: ByteArray): DataResult<Media, DataError.Remote> {
        return remoteDataSource
            .uploadMedia(image)
            .map { it.toMediaModel() }
    }
}