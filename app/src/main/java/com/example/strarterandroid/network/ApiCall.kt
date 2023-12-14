package com.example.strarterandroid.network

import retrofit2.http.GET

interface ApiCall {
    @GET("api/v1/employees")
    fun callApi()
}