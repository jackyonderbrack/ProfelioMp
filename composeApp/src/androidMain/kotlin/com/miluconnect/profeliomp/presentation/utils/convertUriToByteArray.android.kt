package com.miluconnect.profeliomp.presentation.utils

import android.net.Uri
import org.koin.java.KoinJavaComponent.getKoin

actual fun convertUriToByteArray(uri: String): ByteArray? {
    val context = getKoin().get<android.content.Context>()
    return try {
        val contentUri = Uri.parse(uri)
        context.contentResolver.openInputStream(contentUri)?.use { it.readBytes() }
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}
