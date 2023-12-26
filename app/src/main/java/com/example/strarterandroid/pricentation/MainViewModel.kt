package com.example.strarterandroid.pricentation

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.strarterandroid.core.MainIntent
import com.example.strarterandroid.core.MainViewState
import com.example.strarterandroid.network.IMainApi
import com.example.strarterandroid.network.model.GithubReposListModel
import com.example.strarterandroid.network.model.PostModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val mainApiRepoImp: IMainApi
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
            val response = mainApiRepoImp.callApi()
            val ratesResponse = response.body()
            _viewState.value = MainViewState.Success(ratesResponse as List<GithubReposListModel>)
        }
    }

    private fun process() {
        viewModelScope.launch {
            intentChannel.consumeAsFlow().collect {
                when (it) {
                    is MainIntent.callApi -> callApi()
                }
            }

        }
    }
}