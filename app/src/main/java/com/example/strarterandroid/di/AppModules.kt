package com.example.strarterandroid.di

//import androidx.room.Room
import com.example.strarterandroid.network.remote_network.ApiCall
import com.example.strarterandroid.network.remote_network.IApiCall
import com.example.strarterandroid.network.remote_network.MainApiRepoImp
import com.example.strarterandroid.core.RetrofitHelper
//import com.example.strarterandroid.network.local_network.AppDatabase
//import com.example.strarterandroid.network.local_network.GithubRepository
import com.example.strarterandroid.presentation.details_screen.DetailsVm
import com.example.strarterandroid.presentation.issues_screen.IssuesVm
import com.example.strarterandroid.presentation.main_screen.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single {
        RetrofitHelper.createService("https://api.github.com", ApiCall::class.java)
    }
    single<IApiCall> {
        MainApiRepoImp(get())
    }
//    single {
//        Room.databaseBuilder(get(), AppDatabase::class.java, "github_database")
//            .fallbackToDestructiveMigration()
//            .build()
//    }
//
//    single { get<AppDatabase>().githubDao() }
//    single { GithubRepository(get()) }

    viewModel {
        MainViewModel(get())
    }
    viewModel {
        DetailsVm(get())
    }
    viewModel {
        IssuesVm(get())
    }
}