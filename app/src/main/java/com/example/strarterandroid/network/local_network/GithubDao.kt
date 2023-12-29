package com.example.strarterandroid.network.local_network

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import com.example.strarterandroid.network.local_network.model.GithubReposListRoomModel
import com.example.strarterandroid.network.local_network.model.IssuesRoomModel

@Dao
interface GithubDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateReposList(repo: List<GithubReposListRoomModel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateRepoDetails(repo: GithubReposListRoomModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateIssue(issue: IssuesRoomModel)

    @Update
    suspend fun updateReposList(repo: List<GithubReposListRoomModel>)

    @Update
    suspend fun updateRepoDetails(repo: GithubReposListRoomModel)

    @Update
    suspend fun updateIssue(issue: IssuesRoomModel)
}