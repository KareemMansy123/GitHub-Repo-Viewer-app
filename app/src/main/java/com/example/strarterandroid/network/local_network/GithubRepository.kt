package com.example.strarterandroid.network.local_network

import com.example.strarterandroid.network.local_network.model.GithubReposListRoomModel
import com.example.strarterandroid.network.local_network.model.IssuesRoomModel

class GithubRepository(private val githubDao: GithubDao) {
    suspend fun saveOrUpdateReposList(repos: List<GithubReposListRoomModel>) = githubDao.insertOrUpdateReposList(repos)
    suspend fun saveOrUpdateRepoDetails(repos: GithubReposListRoomModel) = githubDao.insertOrUpdateRepoDetails(repos)
    suspend fun saveOrUpdateIssue(issue: IssuesRoomModel) = githubDao.insertOrUpdateIssue(issue)

    suspend fun updateReposList(repos: List<GithubReposListRoomModel>) = githubDao.updateReposList(repos)
    suspend fun updateRepoDetails(repos: GithubReposListRoomModel) = githubDao.updateRepoDetails(repos)
    suspend fun updateIssue(issue: IssuesRoomModel) = githubDao.updateIssue(issue)
}