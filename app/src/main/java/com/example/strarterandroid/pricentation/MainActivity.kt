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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.example.strarterandroid.core.MainIntent
import com.example.strarterandroid.core.MainViewState
import com.example.strarterandroid.network.model.PostModel
import com.example.strarterandroid.ui.theme.StrarterAndroidTheme
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private var text = "remmber"
    private val mainViewModel: MainViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StrarterAndroidTheme {
//                val viewModel = getViewModel<MainViewModel>()
                Render()
                Column {
                    Text(text = text)
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = { send() }) {
                        Text(text = "Send")
                    }
                }
            }
        }
    }

    // MVI pattern
    private fun send() {
        // send data to the view model  by using the intent channel
        lifecycleScope.launch {
            mainViewModel.intentChannel.send(MainIntent.callApi)
        }
    }

    @Composable
    fun Render() {
        // will get data here from the view model by using Flow
        // why Flow? because it's a stream of data that can be observed and if repeated it will give the same result
        LaunchedEffect(Unit) {
            mainViewModel.viewState.collect {
                text = when (it) {
                    is MainViewState.Idle -> "Idle"
                    is MainViewState.Loading -> "Loading"
                    is MainViewState.Success -> (it.data as PostModel).title
                    is MainViewState.Error -> it.error
                }
            }
        }
    }
}
