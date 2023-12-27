package com.example.strarterandroid.pricentation.details_screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.strarterandroid.core.MainViewState
import com.example.strarterandroid.network.IApiCall
import com.example.strarterandroid.network.model.GithubReposListModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class DetailsVm(
    private val mainApiRepoImp: IApiCall
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
    private fun repoDetailsApi(owner: String, repo: String) {
        viewModelScope.launch(exceptionHandler) {
            val response = mainApiRepoImp.reposDetailsApi(owner, repo)
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

    private fun process() {
        viewModelScope.launch {
            intentChannel.consumeAsFlow().collect {
                when (it) {
                    is DetailsIntent.RepoDetails -> repoDetailsApi(it.owner, it.repo)
                }
            }
        }
    }
}