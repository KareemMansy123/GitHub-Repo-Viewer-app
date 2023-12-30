package com.example.strarterandroid.presentation.issues_screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.strarterandroid.core.MainViewState
import com.example.strarterandroid.core.model.IssuesModel
import com.example.strarterandroid.presentation.shared.ErrorUI
import com.example.strarterandroid.presentation.shared.IdleUI
import kotlinx.coroutines.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun IssuesScreen(repo: String, owner: String, issuesVm: IssuesVm, navController: NavHostController) {
    LaunchedEffect(owner, repo) {
        issuesVm.intentChannel.send(IssuesIntent.RepoIssues(repo, owner))
    }
    val state1 = issuesVm.viewState.collectAsState(initial = MainViewState.Idle)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Issues for $repo") },
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
        LazyColumn {
            when (val value = state1.value) {
                is MainViewState.Loading -> item { CircularProgressIndicator() }
                is MainViewState.Success -> {
                    val repos = value.data as List<IssuesModel>
                    items(repos.size) { index ->
                        ScreenUI(repos[index], issuesVm)
                    }
                }

                is MainViewState.Error -> item {
                    ErrorUI(
                        error = value.error,
                        onRetry = { issuesVm.retry(owner, repo) })
                }

                is MainViewState.Idle -> item { IdleUI() }
            }
        }
    }
}

@Composable
fun ScreenUI(response: IssuesModel, issuesVm: IssuesVm) {
    val formattedDate by issuesVm.formattedDate.observeAsState("Loading...")
    LaunchedEffect(key1 = response.updatedAt.toString()) {
        Log.e("loadFormattedDate", "loadFormattedDate: ${response.updatedAt.toString()}")
        issuesVm.intentChannel.send(IssuesIntent.DateIssues(response.closedAt.toString()))
    }
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable(onClick = {
            }),
        elevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = response.title.toString(),
                style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colors.primary
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Owner: ${response.user?.login}",
                style = MaterialTheme.typography.subtitle2,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                // TODO convert the date to a readable format
                text = "Date: $formattedDate",
                style = MaterialTheme.typography.body2
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Stars: ${response.state}", style = MaterialTheme.typography.body2)
        }
    }
}


suspend fun buildDate(stringDate: String): String = withContext(Dispatchers.Default) {
    // Prepare the date formats
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US).apply {
        timeZone = TimeZone.getTimeZone("UTC")
    }
    val outputFormat = SimpleDateFormat("MMMM dd, yyyy 'at' HH:mm:ss", Locale.US).apply {
        timeZone = TimeZone.getDefault()
    }

    try {
        // Parse the date and format it
        val date = inputFormat.parse(stringDate)
        date?.let { outputFormat.format(it) } ?: "Invalid Date"
    } catch (e: ParseException) {
        "Invalid Date"
    }
}