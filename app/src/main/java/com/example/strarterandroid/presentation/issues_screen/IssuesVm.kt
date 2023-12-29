package com.example.strarterandroid.presentation.issues_screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.strarterandroid.core.MainViewState
import com.example.strarterandroid.network.local_network.GithubRepository
import com.example.strarterandroid.network.remote_network.IApiCall
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class IssuesVm(
    private val apiRepoImp: IApiCall,
    private val githubRepository: GithubRepository
) : ViewModel() {
    val intentChannel = Channel<IssuesIntent>(Channel.UNLIMITED)
    private val _viewState = MutableStateFlow<MainViewState>(MainViewState.Idle)
    val viewState: StateFlow<MainViewState> get() = _viewState
    private val _formattedDate = MutableLiveData<String?>()
    val formattedDate: MutableLiveData<String?> = _formattedDate

    // Function to update the formatted date
    fun loadFormattedDate(rawDate: String) {
        Log.e("loadFormattedDate2", "loadFormattedDate: $rawDate", )
        viewModelScope.launch {
            _formattedDate.value = buildDate(rawDate)
        }
    }

    init {
        process()
    }
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        println(throwable.message)
    }
    @SuppressLint("CheckResult")
    private fun repoIssuesApi(owner: String, repo: String) {
        viewModelScope.launch(exceptionHandler) {
            val response = apiRepoImp.repoIssuesApi(owner, repo)
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
                    is IssuesIntent.RepoIssues -> repoIssuesApi(it.owner, it.repo)
                    is IssuesIntent.DateIssues -> Log.e("process", "process: ${it.date}", )
                }
            }
        }
    }

    fun retry(owner: String, repo: String) {
        viewModelScope.launch {
            intentChannel.send(IssuesIntent.RepoIssues(owner, repo))
        }
    }
}