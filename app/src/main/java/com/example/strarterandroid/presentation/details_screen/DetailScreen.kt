package com.example.strarterandroid.presentation.details_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.strarterandroid.core.MainViewState
import com.example.strarterandroid.core.navigation.OuterClass
import com.example.strarterandroid.network.remote_network.model.GithubReposListModel
import com.example.strarterandroid.presentation.shared.ErrorUI
import com.example.strarterandroid.presentation.shared.IdleUI

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailScreen(name: String, owner: String, detailsViewModel: DetailsVm, navController: NavHostController) {
    LaunchedEffect(owner, name) {
        detailsViewModel.intentChannel.send(DetailsIntent.RepoDetails(owner, name))
    }
    val state = detailsViewModel.viewState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Details", style = MaterialTheme.typography.h6) },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = Color.White,
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            when (val viewState = state.value) {
                is MainViewState.Loading -> CircularProgressIndicator()
                is MainViewState.Success -> {
                    val repos = viewState.data as GithubReposListModel
                    DetailsScreenUI(repos, navController)
                }
                is MainViewState.Error -> ErrorUI(error = viewState.error, onRetry = { detailsViewModel.retry(owner, name) })
                is MainViewState.Idle -> IdleUI()
            }
        }
    }
}

@Composable
fun DetailsScreenUI(repos: GithubReposListModel, navController: NavHostController) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "Owner", modifier = Modifier.size(40.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text("Repository: ${repos.name}", style = MaterialTheme.typography.h6)
                    Text("Owner: ${repos.owner.login}", style = MaterialTheme.typography.subtitle1)
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Divider()
            Spacer(modifier = Modifier.height(8.dp))
            Text("Description: ${repos.description}", style = MaterialTheme.typography.body1)
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    val route = OuterClass.NavCommand.Issues(repos.owner.login, repos.name).createRoute()
                    navController.navigate(route)
                },
                modifier = Modifier.align(Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary)
            ) {
                Text("View Issues", color = Color.White)
            }
        }
    }
}

