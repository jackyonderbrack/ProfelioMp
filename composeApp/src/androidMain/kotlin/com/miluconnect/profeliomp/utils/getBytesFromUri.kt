package com.miluconnect.profeliomp.utils

import android.content.Context
import android.net.Uri

fun Context.getBytesFromUri(uri: Uri): ByteArray? {
    return try {
        contentResolver.openInputStream(uri)?.use { it.readBytes() }
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}