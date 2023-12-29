package com.example.strarterandroid.presentation.details_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.strarterandroid.core.MainViewState
import com.example.strarterandroid.core.navigation.OuterClass
import com.example.strarterandroid.network.remote_network.model.GithubReposListModel
import com.example.strarterandroid.presentation.shared.ErrorUI
import com.example.strarterandroid.presentation.shared.IdleUI

@Composable
fun DetailScreen(name: String, owner: String, detailsViewModel: DetailsVm, navController: NavHostController) {
    LaunchedEffect(owner, name) {
        detailsViewModel.intentChannel.send(DetailsIntent.RepoDetails(owner, name))
    }
    val state = detailsViewModel.viewState.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        // Additional UI to show loading, error or details based on the state
        when (val viewState = state.value) {
            is MainViewState.Loading -> CircularProgressIndicator()
            is MainViewState.Success -> {
                val repos = viewState.data as GithubReposListModel
                DetailsScreenUI(repos,navController)
            }
            is MainViewState.Error ->  ErrorUI(error = viewState.error, onRetry = { detailsViewModel.retry(owner, name) })
            is MainViewState.Idle -> IdleUI()
        }
    }

}

@Composable
fun DetailsScreenUI(repos: GithubReposListModel, navController: NavHostController) {
    // Display additional details
    Text("Repository: ${repos.name}", style = MaterialTheme.typography.h6)
    Text("Owner: ${repos.owner.login}", style = MaterialTheme.typography.subtitle1)
    Text("Description: ${repos.description}", style = MaterialTheme.typography.body1)
    // create button to navigate to the issues screen under the repo description centered horizontally
    Spacer(modifier = Modifier.height(16.dp))

    Button(onClick = {val route = OuterClass.NavCommand.Issues(repos.owner.login, repos.name).createRoute()
        navController.navigate(route) }) {
        Text("Issues")
    }
}
