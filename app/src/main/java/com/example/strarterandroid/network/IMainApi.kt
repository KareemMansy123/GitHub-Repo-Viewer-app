package com.example.strarterandroid.network

import com.example.strarterandroid.network.model.GithubReposListModel
import retrofit2.Response

interface IMainApi {
   suspend fun callApi() : Response<List<GithubReposListModel>>
}