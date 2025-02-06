package com.miluconnect.profeliomp.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ColumnScope.ScreenSurface(composable: @Composable () -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .weight(1f),
        color = MaterialTheme.colorScheme.surfaceBright,
        shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp),
    ) {
        Box {
            /* Component inside */
            composable()
        }
    }
}