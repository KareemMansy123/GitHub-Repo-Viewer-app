package com.example.strarterandroid.network.local_network

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.strarterandroid.network.local_network.model.GithubReposListRoomModel
import com.example.strarterandroid.network.local_network.model.IssuesRoomModel

@Database(entities = [GithubReposListRoomModel::class, IssuesRoomModel::class], version = 1)
@TypeConverters(GenericTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun githubDao(): GithubDao
}