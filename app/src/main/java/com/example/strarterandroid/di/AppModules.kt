package com.example.strarterandroid.di

import com.example.strarterandroid.network.ApiCall
import com.example.strarterandroid.network.IApiCall
import com.example.strarterandroid.network.MainApiRepoImp
import com.example.strarterandroid.network.RetrofitHelper
import com.example.strarterandroid.pricentation.details_screen.DetailsVm
import com.example.strarterandroid.pricentation.main_screen.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single {
        RetrofitHelper.createService("https://api.github.com",ApiCall::class.java)
    }
    single<IApiCall> {
        MainApiRepoImp(get())
    }
    viewModel {
        MainViewModel(get())
    }
    viewModel {
        DetailsVm(get())
    }
}