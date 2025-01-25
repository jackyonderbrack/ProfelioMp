package com.miluconnect.profeliomp.presentation.screens.work.addIssue

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.miluconnect.profeliomp.presentation.components.ImagePicker
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AddIssueScreenRoot(
    viewModel: AddIssueViewModel = koinViewModel<AddIssueViewModel>(),
    navController: NavController,
    imagePicker: ImagePicker
) {
    val state by viewModel.state.collectAsState()
    var selectedImageUri by remember { mutableStateOf<String?>(null) }

    AddIssueScreen(
        onPickImage = { imagePicker.pickImage { uri -> selectedImageUri = uri } },
        selectedImageUri = selectedImageUri,
        onSubmit = { }
    )
}

@Composable
fun AddIssueScreen(
    onPickImage: () -> Unit,
    selectedImageUri: String?,
    onSubmit: () -> Unit,
) {
    Column(modifier = Modifier.padding(16.dp)) {
        Button(onClick = { onPickImage() }) {
            Text("Wybierz zdjęcie")
        }

        Spacer(modifier = Modifier.height(16.dp))

        selectedImageUri?.let { uri ->
            AsyncImage(
                model = uri,
                contentDescription = "Wybrane zdjęcie",
                modifier = Modifier.size(200.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onSubmit) {
            Text("Dodaj Issue")
        }
    }
}