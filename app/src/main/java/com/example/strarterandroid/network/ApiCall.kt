package com.example.strarterandroid.network

import com.example.strarterandroid.network.model.GithubReposListModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiCall {
    @GET("/repositories")
   suspend fun reposListApi() : Response<List<GithubReposListModel>>

   @GET("/repos/{owner}/{repo}")
    suspend fun reposDetailsApi(@Path("owner") owner: String, @Path("repo") repo: String) : Response<GithubReposListModel>

}