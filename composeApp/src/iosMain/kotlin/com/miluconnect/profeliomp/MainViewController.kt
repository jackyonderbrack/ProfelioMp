package com.miluconnect.profeliomp

import androidx.compose.ui.window.ComposeUIViewController

fun MainViewController() = ComposeUIViewController( { initKoinIOS() }) { App() }