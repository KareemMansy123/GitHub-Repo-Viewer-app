package com.example.strarterandroid.network.local_network

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.strarterandroid.network.model.GithubReposListModel
import com.example.strarterandroid.network.model.IssuesModel

@Database(entities = [GithubReposListModel::class, IssuesModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun githubDao(): GithubDao
}