package com.example.strarterandroid.network.remote_network

import com.example.strarterandroid.network.model.GithubReposListModel
import com.example.strarterandroid.network.model.IssuesModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiCall {
    @GET("/repositories")
   suspend fun reposListApi() : Response<List<GithubReposListModel>>
   @GET("/repos/{owner}/{repo}")
    suspend fun reposDetailsApi(@Path("owner") owner: String, @Path("repo") repo: String) : Response<GithubReposListModel>
    @GET("/repos/{owner}/{repo}/issues")
    suspend fun repoIssuesApi(@Path("owner") owner: String, @Path("repo") repo: String) : Response<List<IssuesModel>>

}