package com.miluconnect.profeliomp.presentation.screens.blackboard

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.miluconnect.profeliomp.presentation.components.OffersList
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun BlackboardScreenRoot(
    viewModel: BlackboardViewModel = koinViewModel<BlackboardViewModel>()
) {

    val state by viewModel.state.collectAsState()

    BlackboardScreen(
        state = state
    )
}

@Composable
private fun BlackboardScreen(
    state: BlackboardState,
) {
    Column {
        state.errorMessage?.let {
            Text(text = it.asString())
        }
    }
    OffersList(offers = state.offersList)
}