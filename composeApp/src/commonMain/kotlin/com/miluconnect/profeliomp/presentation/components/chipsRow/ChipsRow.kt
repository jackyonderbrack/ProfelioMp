package com.miluconnect.profeliomp.presentation.components.chipsRow

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ChipsRow(
    chips: List<String>,
    selectedChip: String,
    onFilterSelected: (String) -> Unit,
    modifier: Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        chips.forEach { chip ->
            FilterChip(
                colors = FilterChipDefaults.filterChipColors(
                    labelColor = MaterialTheme.colorScheme.onPrimary,
                    selectedLabelColor = MaterialTheme.colorScheme.inverseSurface,
                ),
                selected = selectedChip == chip,
                onClick = { onFilterSelected(chip) },
                label = {
                    Text(
                        text = chip,
                        style = MaterialTheme.typography.bodySmall
                    )
                },
                modifier = Modifier.padding(vertical = 4.dp),
                shape = MaterialTheme.shapes.small
            )
        }
    }
}
