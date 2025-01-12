package com.miluconnect.profeliomp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import profeliomp.composeapp.generated.resources.Res
import profeliomp.composeapp.generated.resources.add_new_project_title
import profeliomp.composeapp.generated.resources.new_project_name

@Composable
fun AddProjectForm() {

    var projectName by rememberSaveable { mutableStateOf("") }

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
        }
    }
}