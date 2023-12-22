package com.example.strarterandroid.di

import com.example.strarterandroid.network.ApiCall
import com.example.strarterandroid.network.IMainApi
import com.example.strarterandroid.network.MainApiRepoImp
import com.example.strarterandroid.network.RetrofitHelper
import com.example.strarterandroid.pricentation.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

val appModule = module {
    single {
            Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(ApiCall::class.java)
    }
    single<IMainApi> {
        MainApiRepoImp(get())
    }
    viewModel {
        MainViewModel(get())
    }
}