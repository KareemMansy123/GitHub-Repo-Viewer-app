package com.example.strarterandroid.network

import com.example.strarterandroid.network.model.GithubReposListModel
import retrofit2.Response

class MainApiRepoImp(
    private val apiCall: ApiCall
) : IApiCall {
    override suspend fun reposListApi() : Response<List<GithubReposListModel>> {
      return  apiCall.reposListApi()
    }

    override suspend fun reposDetailsApi(
        owner: String,
        repo: String
    ): Response<GithubReposListModel> {
        return apiCall.reposDetailsApi(owner, repo)
    }

}