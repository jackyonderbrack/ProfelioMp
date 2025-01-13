package com.miluconnect.profeliomp.presentation.components

import TextFieldWithDatePicker
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.miluconnect.profeliomp.domain.models.ProjectRecurrence
import org.jetbrains.compose.resources.stringResource
import profeliomp.composeapp.generated.resources.Res
import profeliomp.composeapp.generated.resources.add_new_project_title
import profeliomp.composeapp.generated.resources.new_project_end_date
import profeliomp.composeapp.generated.resources.new_project_max_issues
import profeliomp.composeapp.generated.resources.new_project_name

@Composable
fun AddProjectForm() {

    var projectName by rememberSaveable { mutableStateOf("") }
    var numberOfIssues by rememberSaveable { mutableStateOf("") }
    var projectRecurrence by rememberSaveable { mutableStateOf(ProjectRecurrence.DAILY.name) }
    var isMaxNumberOfIssuesError by rememberSaveable { mutableStateOf(false) }
//    var endDate by rememberSaveable { mutableStateOf(Date().time) }

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
            Text(
                text = stringResource(Res.string.add_new_project_title),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.displaySmall
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = stringResource(Res.string.new_project_name),
                style = MaterialTheme.typography.bodyLarge
            )

            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = projectName,
                onValueChange = { projectName = it },
                placeholder = { Text(text = "e.g. New kitchen project") }
            )

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                val maxIssuesNumber = 3

                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = stringResource(Res.string.new_project_max_issues),
                        style = MaterialTheme.typography.bodyLarge
                    )
                    TextField(
                        modifier = Modifier.width(128.dp),
                        value = numberOfIssues,
                        onValueChange = {
                            if (it.length < maxIssuesNumber) {
                                isMaxNumberOfIssuesError = false
                                numberOfIssues = it
                            } else {
                                isMaxNumberOfIssuesError = true
                            }
                        },
                        trailingIcon = {
                            if (isMaxNumberOfIssuesError) {
                                Icon(
                                    imageVector = Icons.Filled.Info,
                                    contentDescription = "Error",
                                    tint = MaterialTheme.colorScheme.error
                                )
                            }
                        },
                        placeholder = { Text(text = "e.g. 1") },
                        isError = isMaxNumberOfIssuesError,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                }
                AddProjectDropdownMenu {
                    projectRecurrence = it
                }
            }
            if (isMaxNumberOfIssuesError) {
                Text(
                    text = "You cannot have more than 3 issues per day.",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = stringResource(Res.string.new_project_end_date),
                style = MaterialTheme.typography.bodyLarge
            )
            TextFieldWithDatePicker()
        }
    }
}