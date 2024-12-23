package com.miluconnect.profeliomp

import androidx.compose.ui.window.ComposeUIViewController
import com.miluconnect.profeliomp.di.initKoinIOS

fun MainViewController() = ComposeUIViewController( { initKoinIOS() }) { App() }