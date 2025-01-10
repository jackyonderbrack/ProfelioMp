package com.miluconnect.profeliomp.presentation.components.chipsRow

sealed interface ChipsRowIntent {
    data class OnChipSelectedChange(val chipText: String): ChipsRowIntent
}