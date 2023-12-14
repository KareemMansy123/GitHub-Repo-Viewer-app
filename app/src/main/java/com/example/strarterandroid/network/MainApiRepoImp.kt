package com.example.strarterandroid.network

class MainApiRepoImp(
    private val apiCall: ApiCall
) : IMainApi {
    override fun callApi() {
        apiCall.callApi()
    }
}