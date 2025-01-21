package com.miluconnect.profeliomp.presentation.screens.projects.addProject

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.miluconnect.profeliomp.presentation.screens.projects.components.AddProjectForm
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import profeliomp.composeapp.generated.resources.Res
import profeliomp.composeapp.generated.resources.add_new_project_title

@Composable
fun AddProjectScreenRoot(
    viewModel: AddProjectViewModel = koinViewModel<AddProjectViewModel>(),
    navController: NavController,
) {

    val state by viewModel.state.collectAsState()

    AddProjectScreen(
        state = state,
        onIntent = { intent ->
            when (intent) {
                is AddProjectIntent.SubmitForm -> viewModel.onIntent(intent) {
                    navController.popBackStack()
                }
                is AddProjectIntent.DismissForm -> {
                    navController.popBackStack()
                }
            }
        }
    )
}

@Composable
fun AddProjectScreen(
    onIntent: (AddProjectIntent) -> Unit,
    state: AddProjectState
) {
    Column(
        horizontalAlignment = Alignment.Start,
    ) {
        Text(
            text = stringResource(Res.string.add_new_project_title),
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.padding(16.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.size(128.dp))
            } else {
                AddProjectForm(
                    onSubmit = { newProject ->
                        onIntent(AddProjectIntent.SubmitForm(newProject))
                    },
                    onDismiss = {
                        onIntent(AddProjectIntent.DismissForm)
                    }
                )
            }
        }


    }
}