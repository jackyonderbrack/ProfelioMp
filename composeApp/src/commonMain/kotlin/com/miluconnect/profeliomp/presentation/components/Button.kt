package com.miluconnect.profeliomp.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

enum class ButtonType {
    FILLED, OUTLINED, TEXT, ELEVATED, TONAL
}

@Composable
fun Button(onClick: () -> Unit, buttonType: ButtonType = ButtonType.FILLED, label: String) {
    when (buttonType) {
        ButtonType.OUTLINED -> {
            OutlinedButton(onClick = { onClick() }) {
                Text(label)
            }
        }
        ButtonType.TEXT -> {
            TextButton(
                onClick = { onClick() }
            ) {
                Text(label)
            }
        }
        ButtonType.ELEVATED -> {
            ElevatedButton(onClick = { onClick() }) {
                Text(label)
            }
        }
        ButtonType.TONAL -> {
            FilledTonalButton(onClick = { onClick() }) {
                Text(label)
            }
        }
        else -> {
            Button(onClick = { onClick() }) {
                Text(label)
            }
        }
    }
}