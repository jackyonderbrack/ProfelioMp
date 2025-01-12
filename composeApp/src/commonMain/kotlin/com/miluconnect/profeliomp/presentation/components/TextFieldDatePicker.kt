import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.datetime.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldDatePicker() {
    var showDialog by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = null)

    Button(onClick = { showDialog = true }) {
        Text("Pick a Date")
    }

    if (showDialog) {
        DatePickerDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        showDialog = false
                        val selectedDateMillis = datePickerState.selectedDateMillis?.toInt()
                        if (selectedDateMillis != null) {
                            val selectedDate = LocalDate.fromEpochDays(selectedDateMillis / (24 * 60 * 60 * 1000))
                            println("Selected Date: $selectedDate")
                        }
                    }
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("CANCEL")
                }
            }
        ) {
            DatePicker(
                state = datePickerState,
                modifier = Modifier.padding(16.dp),
                showModeToggle = true
            )
        }
    }
}
