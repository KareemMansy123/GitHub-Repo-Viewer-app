package com.example.strarterandroid.pricentation

import androidx.lifecycle.ViewModel
import com.example.strarterandroid.network.MainApiRepoImp

class MainViewModel(
    private val mainApiRepoImp: MainApiRepoImp
): ViewModel() {
    fun callApi() {
        mainApiRepoImp.callApi()
    }
}