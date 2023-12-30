package com.example.strarterandroid.presentation.details_screen

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.strarterandroid.App
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

class DetailsVm(
    private val apiRepoImp: IApiCall,
    private val githubRepository: GithubRepository
) : ViewModel() {
    val intentChannel = Channel<DetailsIntent>(Channel.UNLIMITED)
    private val _viewState = MutableStateFlow<MainViewState>(MainViewState.Idle)
    val viewState: StateFlow<MainViewState> get() = _viewState
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        println(throwable.message)
    }

    init {
        process()
    }

    @SuppressLint("CheckResult")
    fun callApi(owner: String, repo: String) {
        viewModelScope.launch(exceptionHandler) {
            if (isNetworkAvailable(App.appContext)) {
                fetchReposFromApi(owner, repo)
            } else {
                fetchRepoFromDatabase(owner, repo)
            }
        }
    }

    @SuppressLint("CheckResult")
    private fun fetchReposFromApi(owner: String, repo: String) {
        viewModelScope.launch(exceptionHandler) {
            val response = apiRepoImp.reposDetailsApi(owner, repo)
            if (response.isSuccessful) {
                response.body()?.let { body ->
                    _viewState.value = MainViewState.Success(body)
                } ?: run {
                    _viewState.value = MainViewState.Error("Response body is null")
                }
            } else {
                _viewState.value =
                    MainViewState.Error("Failed to load details: ${response.errorBody()?.string()}")
            }
        }
    }

    private suspend fun fetchRepoFromDatabase(owner: String, repo: String) {
        val repoFromDb = githubRepository.getRepoByOwnerAndName(owner, repo)
        repoFromDb?.let {
            _viewState.value = MainViewState.Success(it)
        } ?: run {
            _viewState.value = MainViewState.Error("No data in database")
        }
    }

    private fun process() {
        viewModelScope.launch {
            intentChannel.consumeAsFlow().collect {
                when (it) {
                    is DetailsIntent.RepoDetails -> callApi(it.owner, it.repo)
                }
            }
        }
    }
    fun retry(owner: String, repo: String) {
        viewModelScope.launch {
            intentChannel.send(DetailsIntent.RepoDetails(owner, repo))
        }
    }
}