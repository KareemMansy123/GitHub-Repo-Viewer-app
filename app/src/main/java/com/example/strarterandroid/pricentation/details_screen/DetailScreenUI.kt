package com.example.strarterandroid.pricentation.details_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.strarterandroid.core.MainViewState
import com.example.strarterandroid.network.model.GithubReposListModel

@Composable
fun DetailScreen(name: String, owner: String, detailsViewModel: DetailsVm) {
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

                // Display additional details
                Text("Repository: ${repos.name}", style = MaterialTheme.typography.h6)
                Text("Owner: ${repos.owner.login}", style = MaterialTheme.typography.subtitle1)
                Text("Description: ${repos.description}", style = MaterialTheme.typography.body1)
            }
            is MainViewState.Error -> Text("Error: ${viewState.error}")
            else -> Unit
        }
    }
}