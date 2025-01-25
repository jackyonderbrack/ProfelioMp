package com.miluconnect.profeliomp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.miluconnect.profeliomp.presentation.components.ImagePicker
import com.miluconnect.profeliomp.presentation.components.provideImagePicker

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val imagePicker: ImagePicker = provideImagePicker(this)

        setContent {
            App(imagePicker = imagePicker)
        }
    }
}