package com.example.strarterandroid.di

import com.example.strarterandroid.network.ApiCall
import com.example.strarterandroid.network.IMainApi
import com.example.strarterandroid.network.MainApiRepoImp
import com.example.strarterandroid.network.RetrofitHelper
import com.example.strarterandroid.pricentation.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single {
        RetrofitHelper.createService("https://api.github.com",ApiCall::class.java)
    }
    single<IMainApi> {
        MainApiRepoImp(get())
    }
    viewModel {
        MainViewModel(get())
    }
}