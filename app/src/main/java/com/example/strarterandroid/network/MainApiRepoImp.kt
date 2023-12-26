package com.example.strarterandroid.network

import com.example.strarterandroid.network.model.GithubReposListModel
import retrofit2.Response

class MainApiRepoImp(
    private val apiCall: ApiCall
) : IMainApi {
    override suspend fun callApi() : Response<List<GithubReposListModel>> {
      return  apiCall.reposListApi()
    }
}