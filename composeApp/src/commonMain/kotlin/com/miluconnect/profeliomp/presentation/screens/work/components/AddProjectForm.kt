package com.miluconnect.profeliomp.presentation.screens.work.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.miluconnect.profeliomp.domain.models.Project
import com.miluconnect.profeliomp.presentation.components.ButtonType
import com.miluconnect.profeliomp.presentation.components.ImagePicker
import com.miluconnect.profeliomp.presentation.components.ProfelioButton
import org.jetbrains.compose.resources.stringResource
import profeliomp.composeapp.generated.resources.Res
import profeliomp.composeapp.generated.resources.new_project_create_button_label
import profeliomp.composeapp.generated.resources.new_project_custom_label
import profeliomp.composeapp.generated.resources.new_project_dismiss_button_label
import profeliomp.composeapp.generated.resources.new_project_email_invitation_label
import profeliomp.composeapp.generated.resources.new_project_email_invitation_notice
import profeliomp.composeapp.generated.resources.new_project_end_date
import profeliomp.composeapp.generated.resources.new_project_name
import profeliomp.composeapp.generated.resources.new_project_place

@Composable
fun AddProjectForm(
    onSubmit: (Project, String) -> Unit,
    onDismiss: () -> Unit,
    imagePicker: ImagePicker,
) {
    var projectState by remember {
        mutableStateOf(
            Project(
                id = null,
                title = "",
                customer = "",
                startDate = null,
                endDate = "",
                city = "",
                status = null,
                labels = emptyList(),
                pictureUrl = null
            )
        )
    }

    var selectedImageUri by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState()),
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .background(MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            /* TITLE FIELD */
            Text(
                text = stringResource(Res.string.new_project_name),
                style = MaterialTheme.typography.bodyLarge
            )

            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = projectState.title,
                onValueChange = { projectState = projectState.copy(title = it) },
                placeholder = { Text(text = "e.g. New kitchen project") }
            )

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = { imagePicker.pickImage { uri -> selectedImageUri = uri } }) {
                    Text(text = "Wybierz zdjęcie projektu")
                }
                selectedImageUri?.let { uri ->
                    AsyncImage(
                        model = uri,
                        contentDescription = "Podgląd zdjęcia",
                        modifier = Modifier.size(56.dp)
                    )
                }

            }

            Spacer(modifier = Modifier.height(8.dp))

            /* END DATE FIELD */
            Text(
                text = stringResource(Res.string.new_project_end_date),
                style = MaterialTheme.typography.bodyLarge
            )

            TextFieldWithDatePicker(
                selectedDate = projectState.endDate,
                onDateSelected = { projectState = projectState.copy(endDate = it) }
            )

            /* CITY FIELD */
            Text(
                text = stringResource(Res.string.new_project_place),
                style = MaterialTheme.typography.bodyLarge
            )

            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = projectState.city,
                onValueChange = { projectState = projectState.copy(city = it) },
                placeholder = { Text(text = "e.g. Rybnik") }
            )

            /* LABEL FIELD */
            Text(
                text = stringResource(Res.string.new_project_custom_label),
                style = MaterialTheme.typography.bodyLarge
            )

            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = projectState.labels?.joinToString(", ") ?: "",
                onValueChange = {
                    projectState = projectState.copy(labels = it.split(",").map(String::trim))
                },
                placeholder = { Text(text = "e.g. Housework, Outdoor") }
            )

            Spacer(modifier = Modifier.height(8.dp))

            /* CUSTOMER FIELD */
            Text(
                text = stringResource(Res.string.new_project_email_invitation_label),
                style = MaterialTheme.typography.bodyLarge
            )

            Text(
                text = stringResource(Res.string.new_project_email_invitation_notice),
                style = MaterialTheme.typography.bodySmall
            )

            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = projectState.customer,
                onValueChange = { projectState = projectState.copy(customer = it) },
            )

            Spacer(modifier = Modifier.height(16.dp))

            /* ACTION BUTTONS */
            Row(
                modifier = Modifier.align(Alignment.End),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                ProfelioButton(
                    modifier = Modifier.weight(1f).height(48.dp),
                    onClick = { onDismiss() },
                    label = stringResource(Res.string.new_project_dismiss_button_label),
                    buttonType = ButtonType.TEXT
                )
                ProfelioButton(
                    modifier = Modifier.weight(1f).height(48.dp),
                    onClick = { selectedImageUri?.let { onSubmit(projectState, it) } },
                    label = stringResource(Res.string.new_project_create_button_label)
                )
            }
        }
    }
}
