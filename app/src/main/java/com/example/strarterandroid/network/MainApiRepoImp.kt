package com.example.strarterandroid.network

import com.example.strarterandroid.network.model.PostModel
import io.reactivex.rxjava3.core.Single
import retrofit2.Call

class MainApiRepoImp(
    private val apiCall: ApiCall
) : IMainApi {
    override fun callApi() : Single<PostModel> {
      return  apiCall.callApi()
    }
}