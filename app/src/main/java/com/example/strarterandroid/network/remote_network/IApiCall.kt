package com.example.strarterandroid.network.remote_network

import com.example.strarterandroid.network.model.GithubReposListModel
import com.example.strarterandroid.network.model.IssuesModel
import retrofit2.Response

interface IApiCall {
   suspend fun reposListApi() : Response<List<GithubReposListModel>>
    suspend fun reposDetailsApi(owner: String, repo: String) : Response<GithubReposListModel>
    suspend fun repoIssuesApi(owner: String, repo: String) : Response<List<IssuesModel>>
}