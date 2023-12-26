package com.example.strarterandroid.pricentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.example.strarterandroid.core.MainIntent
import com.example.strarterandroid.core.MainViewState
import com.example.strarterandroid.network.model.GithubReposListModel
import com.example.strarterandroid.ui.theme.StrarterAndroidTheme
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StrarterAndroidTheme {
                val viewModel = getViewModel<MainViewModel>()
                send(viewModel)
                Render(viewModel)
            }
        }
    }

    // MVI pattern
    private fun send(viewModel: MainViewModel) {
        lifecycleScope.launch {
            viewModel.intentChannel.send(MainIntent.callApi)
        }
    }

    @Composable
    fun Render(viewModel: MainViewModel) {
        val reposList = viewModel.viewState.collectAsState(initial = MainViewState.Idle)

        LazyColumn {
            when (val state = reposList.value) {
                is MainViewState.Loading -> item { Text("Loading...") }
                is MainViewState.Success -> {
                    val repos = state.data as List<GithubReposListModel>
                    items(repos.size) { index ->
                        ListItem(repos[index])
                    }
                }
                is MainViewState.Error -> item { Text("Error: ${state.error}") }
                is MainViewState.Idle -> item { Text("Idle") }
            }
        }
    }

    @Composable
    fun ListItem(repo: GithubReposListModel) {
        Card(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .clickable(onClick = {
                    // Handle item click, for example, show a toast or navigate
                    Log.d("MainActivity", "Clicked on ${repo.fullName}")
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
}
