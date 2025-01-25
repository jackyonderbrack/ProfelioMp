package com.miluconnect.profeliomp.presentation.components

import android.net.Uri
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts

class AndroidImagePicker(private val activity: ComponentActivity) : ImagePicker {
    private var callback: ((String?) -> Unit)? = null

    private val pickImageLauncher = activity.registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        callback?.invoke(uri?.toString())
    }

    override fun pickImage(callback: (String?) -> Unit) {
        this.callback = callback
        pickImageLauncher.launch("image/*")
    }
}

actual fun provideImagePicker(): ImagePicker {
    throw IllegalStateException("provideImagePicker must be called with an activity context")
}

fun provideImagePicker(activity: ComponentActivity): ImagePicker = AndroidImagePicker(activity)
