package com.example.strarterandroid.network

import com.example.strarterandroid.network.model.PostModel
import io.reactivex.rxjava3.core.Single

interface IMainApi {
    fun callApi() : Single<PostModel>
}