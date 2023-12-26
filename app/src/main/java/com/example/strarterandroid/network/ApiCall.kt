package com.example.strarterandroid.network

import com.example.strarterandroid.network.model.GithubReposListModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiCall {
    @GET("/repositories")
   suspend fun reposListApi() : Response<List<GithubReposListModel>>
}