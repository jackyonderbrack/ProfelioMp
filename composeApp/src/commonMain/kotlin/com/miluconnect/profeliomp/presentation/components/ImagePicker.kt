package com.miluconnect.profeliomp.presentation.components

interface ImagePicker {
    fun pickImage(callback: (String?) -> Unit)
}

expect fun provideImagePicker(): ImagePicker