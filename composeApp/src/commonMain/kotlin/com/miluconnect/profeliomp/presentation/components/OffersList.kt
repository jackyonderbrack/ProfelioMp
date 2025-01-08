package com.miluconnect.profeliomp.presentation.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun OffersList() {
    LazyColumn {
        item {
            Text(text = "Find a job for you")
        }
    }
}