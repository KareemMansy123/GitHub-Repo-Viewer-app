package com.example.strarterandroid.network

import com.example.strarterandroid.network.model.GithubReposListModel
import com.example.strarterandroid.network.model.PostModel
import io.reactivex.rxjava3.core.Single
import retrofit2.Response

interface IMainApi {
   suspend fun callApi() : Response<List<GithubReposListModel>>
}