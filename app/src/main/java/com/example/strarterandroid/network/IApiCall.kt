package com.example.strarterandroid.network

import com.example.strarterandroid.network.model.GithubReposListModel
import retrofit2.Response

interface IApiCall {
   suspend fun reposListApi() : Response<List<GithubReposListModel>>

    suspend fun reposDetailsApi(owner: String, repo: String) : Response<GithubReposListModel>
}