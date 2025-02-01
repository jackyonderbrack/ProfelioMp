package com.miluconnect.profeliomp.data.network

import com.miluconnect.profeliomp.data.dto.MediaDto
import com.miluconnect.profeliomp.domain.core.DataError
import com.miluconnect.profeliomp.domain.core.DataResult
import com.miluconnect.profeliomp.domain.models.Media

interface RemoteMediaDataSource {
    suspend fun uploadMedia(media: Media): DataResult<MediaDto, DataError.Remote>
}

class RemoteMediaDataSourceImpl {
}