package com.miluconnect.profeliomp.presentation.screens.projects.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.miluconnect.profeliomp.domain.models.Project
import com.miluconnect.profeliomp.domain.models.ProjectRecurrence
import com.miluconnect.profeliomp.presentation.components.Button
import com.miluconnect.profeliomp.presentation.components.ButtonType
import org.jetbrains.compose.resources.stringResource
import profeliomp.composeapp.generated.resources.Res
import profeliomp.composeapp.generated.resources.new_project_create_button_label
import profeliomp.composeapp.generated.resources.new_project_custom_label
import profeliomp.composeapp.generated.resources.new_project_dismiss_button_label
import profeliomp.composeapp.generated.resources.new_project_email_invitation_label
import profeliomp.composeapp.generated.resources.new_project_email_invitation_notice
import profeliomp.composeapp.generated.resources.new_project_email_invitation_placeholder
import profeliomp.composeapp.generated.resources.new_project_end_date
import profeliomp.composeapp.generated.resources.new_project_max_issues
import profeliomp.composeapp.generated.resources.new_project_name
import profeliomp.composeapp.generated.resources.new_project_place

@Composable
fun AddProjectForm(
    navController: NavController,
    onSubmit: (Project) -> Unit
) {






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
            /* TITLE */
            var title by rememberSaveable { mutableStateOf("") }

            Text(
                text = stringResource(Res.string.new_project_name),
                style = MaterialTheme.typography.bodyLarge
            )

            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = title,
                onValueChange = { title = it },
                placeholder = { Text(text = "e.g. New kitchen project") }
            )

            Spacer(modifier = Modifier.height(4.dp))

            /* END DATE FIELD */
            var endDate by rememberSaveable { mutableStateOf("") }

            Text(
                text = stringResource(Res.string.new_project_end_date),
                style = MaterialTheme.typography.bodyLarge
            )
            TextFieldWithDatePicker(
                selectedDate = endDate,
                onDateSelected = { endDate = it }
            )

            /* CITY FIELD */
            var city by rememberSaveable { mutableStateOf("") }

            Text(
                text = stringResource(Res.string.new_project_place),
                style = MaterialTheme.typography.bodyLarge
            )
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = city,
                onValueChange = { city = it },
                placeholder = { Text(text = "e.g. Rybnik") }
            )

            /* LABEL FIELD */
            var label by rememberSaveable { mutableStateOf("") }

            Text(
                text = stringResource(Res.string.new_project_custom_label),
                style = MaterialTheme.typography.bodyLarge
            )
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = label,
                onValueChange = { label = it },
                placeholder = { Text(text = "e.g. Housework, Outdoor") }
            )

            Spacer(modifier = Modifier.height(8.dp))

            /* CUSTOMER FIELD */
            var customer by rememberSaveable { mutableStateOf("") }

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
                value = customer,
                onValueChange = { customer = it },
            )

            Spacer(modifier = Modifier.height(16.dp))

            /* ACTION BUTTONS */
            Row(
                modifier = Modifier.align(Alignment.End),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    modifier = Modifier.weight(1f).height(48.dp),
                    onClick = { navController.popBackStack() },
                    label = stringResource(Res.string.new_project_dismiss_button_label),
                    buttonType = ButtonType.TEXT
                )
                Button(
                    modifier = Modifier.weight(1f).height(48.dp),
                    onClick = { handleSubmitClick(
                        newTitle = title,
                        newEndDate = endDate,
                        newCity = city,
                        newCustomer = customer,
                        newLabels = listOf("bug"),
                        onSubmit = onSubmit
                    ) },
                    label = stringResource(Res.string.new_project_create_button_label)
                )
            }
        }
    }
}

private fun handleSubmitClick(
    newTitle: String,
    newCustomer: String,
    newEndDate: String,
    newCity: String,
    newLabels: List<String>,
    onSubmit: (Project) -> Unit
) {
    val newProject = Project(
        title = newTitle,
        customer = newCustomer,
        endDate = newEndDate,
        city = newCity,
        labels = newLabels
    )

    onSubmit(newProject)
}

