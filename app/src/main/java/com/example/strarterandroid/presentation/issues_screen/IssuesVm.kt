package com.example.strarterandroid.presentation.issues_screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.strarterandroid.App
import com.example.strarterandroid.core.MainViewState
import com.example.strarterandroid.network.local_network.GithubRepository
import com.example.strarterandroid.core.model.IssuesModel
import com.example.strarterandroid.network.local_network.IGithubRepository
import com.example.strarterandroid.network.remote_network.IApiCall
import com.example.strarterandroid.presentation.shared.network_checker.NetworkChecker
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import retrofit2.Response

class IssuesVm(
    private val apiRepoImp: IApiCall,
    private val githubRepository: IGithubRepository,
    private val networkChecker: NetworkChecker
) : ViewModel() {
    val intentChannel = Channel<IssuesIntent>(Channel.UNLIMITED)
    private val _viewState = MutableStateFlow<MainViewState>(MainViewState.Idle)
    val viewState: StateFlow<MainViewState> get() = _viewState
    private val _formattedDate = MutableLiveData<String?>()
    val formattedDate: MutableLiveData<String?> = _formattedDate
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        println(throwable.message)
    }
    init {
        process()
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

    @SuppressLint("CheckResult")
    private fun repoIssuesApi(owner: String, repo: String) {
        viewModelScope.launch(exceptionHandler) {
            if (networkChecker.isNetworkAvailable()) {
                fetchIssuesFromApi(owner, repo)
            } else {
                fetchIssuesFromDatabase()
            }
        }
    }

    private suspend fun fetchIssuesFromApi(owner: String, repo: String) {
        try {
            val response = apiRepoImp.repoIssuesApi(owner, repo)
            if (response.isSuccessful) {
                processSuccessfulResponse(response)
            } else {
                _viewState.value = MainViewState.Error("Failed to load details: ${response.errorBody()?.string()}")
            }
        } catch (e: Exception) {
            _viewState.value = MainViewState.Error("Network error: ${e.message}")
        }
    }

    private suspend fun processSuccessfulResponse(response: Response<List<IssuesModel>>) {
        response.body()?.let { body ->
            _viewState.value = MainViewState.Success(body)
            githubRepository.saveOrUpdateIssue(body)
        } ?: run {
            _viewState.value = MainViewState.Error("Response body is null")
        }
    }

    private suspend fun fetchIssuesFromDatabase() {
        val issues = githubRepository.getIssuesFromDb()
        if (issues.isNotEmpty()) {
            _viewState.value = MainViewState.Success(issues)
        } else {
            _viewState.value = MainViewState.Error("No data available")
        }
    }

    fun retry(owner: String, repo: String) {
        viewModelScope.launch {
            intentChannel.send(IssuesIntent.RepoIssues(owner, repo))
        }
    }
}