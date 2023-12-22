package com.example.strarterandroid.core

sealed class MainViewState {
    // ideal state
    object Idle: MainViewState()
    // loading state
    object Loading: MainViewState()
    // success state
    data class Success(val data: Any): MainViewState()
    // error state
    data class Error(val error: String): MainViewState()
}