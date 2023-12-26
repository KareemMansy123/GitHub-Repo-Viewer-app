package com.example.strarterandroid.pricentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
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
                val text = remember { mutableStateOf("remember") }

                Render(viewModel,text)
                Column {
                    Text(text = text.value)
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = { send(viewModel) }) {
                        Text(text = "Send")
                    }
                }
            }
        }
    }

    // MVI pattern
    private fun send(viewModel: MainViewModel) {
        // send data to the view model  by using the intent channel
        lifecycleScope.launch {
            viewModel.intentChannel.send(MainIntent.callApi)
        }
    }

    @Composable
    fun Render(viewModel: MainViewModel,text: MutableState<String>) {
        // will get data here from the view model by using Flow
        // why Flow? because it's a stream of data that can be observed and if repeated it will give the same result
        LaunchedEffect(Unit) {
            viewModel.viewState.collect {
                text.value = when (it) {
                    is MainViewState.Idle -> "Idle"
                    is MainViewState.Loading -> "Loading"
                    is MainViewState.Success -> (it.data as List<GithubReposListModel>).first().fullName
                    is MainViewState.Error -> it.error
                }
            }
        }
    }
}
