package com.miluconnect.profeliomp

import androidx.compose.ui.window.ComposeUIViewController
import com.miluconnect.profeliomp.di.initKoin
import com.miluconnect.profeliomp.di.iosModule

fun MainViewController() = ComposeUIViewController( { initKoin(iosModule) }) { App() }