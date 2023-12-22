package com.example.strarterandroid.network

import com.example.strarterandroid.network.model.PostModel
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface ApiCall {
    @GET("posts/1")
    fun callApi() : Single<PostModel>
}