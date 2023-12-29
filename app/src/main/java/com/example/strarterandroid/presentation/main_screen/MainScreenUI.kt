package com.example.strarterandroid.presentation.main_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.strarterandroid.core.MainViewState
import com.example.strarterandroid.core.navigation.OuterClass
import com.example.strarterandroid.network.remote_network.model.GithubReposListModel
import com.example.strarterandroid.presentation.shared.ErrorUI
import com.example.strarterandroid.presentation.shared.IdleUI

@Composable
fun MainScreenUI(navController: NavController, viewModel: MainViewModel) {
    val reposList = viewModel.viewState.collectAsState(initial = MainViewState.Idle)

    LazyColumn {
        when (val state = reposList.value) {
            is MainViewState.Loading -> item { CircularProgressIndicator() }
            is MainViewState.Success -> {
                val repos = state.data as List<GithubReposListModel>
                items(repos.size) { index ->
                    ListItem(repos[index],navController)
                }
            }
            is MainViewState.Error -> item { ErrorUI(error = state.error, onRetry = { viewModel.retry() }) }
            is MainViewState.Idle -> item { IdleUI() }
        }
    }
}

@Composable
fun ListItem(repo: GithubReposListModel, navController: NavController) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable(onClick = {
                val detailCommand = OuterClass.NavCommand.Detail(repo.name, repo.owner.login)
                navController.navigate(detailCommand.createRoute())

            }),
        elevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = repo.name,
                style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colors.primary
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Owner: ${repo.owner.login}",
                style = MaterialTheme.typography.subtitle2,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Description: ${repo.description ?: "No description provided."}",
                style = MaterialTheme.typography.body2
            )
            // we don't have starsCount in our model
            // Spacer(modifier = Modifier.height(8.dp))
            // Text(text = "Stars: ${repo.starsCount}", style = MaterialTheme.typography.body2)
        }
    }
}


