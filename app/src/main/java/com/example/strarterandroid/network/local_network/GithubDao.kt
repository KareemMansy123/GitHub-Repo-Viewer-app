package com.example.strarterandroid.network.local_network

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import com.example.strarterandroid.network.model.GithubReposListModel
import com.example.strarterandroid.network.model.IssuesModel

@Dao
interface GithubDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateReposList(repo: List<GithubReposListModel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateRepoDetails(repo: GithubReposListModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateIssue(issue: IssuesModel)

    @Update
    suspend fun updateReposList(repo: List<GithubReposListModel>)

    @Update
    suspend fun updateRepoDetails(repo: GithubReposListModel)

    @Update
    suspend fun updateIssue(issue: IssuesModel)
}