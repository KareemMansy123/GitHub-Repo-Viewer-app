package com.example.strarterandroid.presentation.main_screen

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.strarterandroid.App.Companion.appContext
import com.example.strarterandroid.core.MainViewState
import com.example.strarterandroid.presentation.shared.isNetworkAvailable
import com.example.strarterandroid.network.local_network.GithubRepository
import com.example.strarterandroid.network.remote_network.IApiCall
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val mainApiRepoImp: IApiCall,
    private val githubRepository: GithubRepository
) : ViewModel() {
    val intentChannel = Channel<MainIntent>(Channel.UNLIMITED)
    private val _viewState = MutableStateFlow<MainViewState>(MainViewState.Idle)
    val viewState: StateFlow<MainViewState> get() = _viewState

    init {
        process()
    }
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        println(throwable.message)
    }
    @SuppressLint("CheckResult")
    fun callApi() {
        viewModelScope.launch(exceptionHandler) {
            if (isNetworkAvailable(appContext)) {
                fetchReposFromApi()
            } else {
                fetchReposFromDatabase()
            }
        }
    }

    private suspend fun fetchReposFromApi() {
        try {
            val response = mainApiRepoImp.reposListApi()
            if (response.isSuccessful) {
                response.body()?.let { body ->
                    _viewState.value = MainViewState.Success(body)
                    githubRepository.saveOrUpdateReposList(body)
                } ?: run {
                    _viewState.value = MainViewState.Error("Response body is null")
                }
            } else {
                _viewState.value =
                    MainViewState.Error("Failed to load details: ${response.errorBody()?.string()}")
            }
        } catch (e: Exception) {
            _viewState.value = MainViewState.Error("Network error: ${e.message}")
        }
    }

    private suspend fun fetchReposFromDatabase() {
        val reposList = githubRepository.getReposFromDb()
        if (reposList.isNotEmpty()) {
            _viewState.value = MainViewState.Success(reposList)
        } else {
            _viewState.value = MainViewState.Error("No data available")
        }
    }

    private fun process() {
        viewModelScope.launch {
            intentChannel.consumeAsFlow().collect {
                when (it) {
                    is MainIntent.ReposList -> callApi()
                }
            }

        }
    }
    fun retry() {
        viewModelScope.launch {
            intentChannel.send(MainIntent.ReposList)
        }
    }
}