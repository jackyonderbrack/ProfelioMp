package com.miluconnect.profeliomp.presentation.components.chipsRow

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class ChipsRowViewModel : ViewModel() {

    private val _state = MutableStateFlow(ChipsRowState())
    val state: StateFlow<ChipsRowState> get() = _state

    fun onIntent(intent: ChipsRowIntent) {
        when (intent) {
            is ChipsRowIntent.OnChipSelectedChange -> {
                println("Chip selected: ${intent.chipText}") // Debug log
                _state.update {
                    val updatedState = it.copy(selectedChip = intent.chipText)
                    println("Updated state: $updatedState") // Debug log
                    updatedState
                }

            }
        }
    }


}