package com.miluconnect.profeliomp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.miluconnect.profeliomp.presentation.screens.login.LoginScreen
import com.miluconnect.profeliomp.presentation.screens.login.LoginScreenRoot
import com.miluconnect.profeliomp.presentation.screens.login.LoginViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {
    MaterialTheme {
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            LoginScreenRoot(
                onLoginClick = {}
            )
        }
    }
}