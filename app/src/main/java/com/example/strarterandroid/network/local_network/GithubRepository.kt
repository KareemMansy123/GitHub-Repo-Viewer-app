package com.example.strarterandroid.network.local_network

import com.example.strarterandroid.network.model.GithubReposListModel
import com.example.strarterandroid.network.model.IssuesModel

class GithubRepository(private val githubDao: GithubDao) {
    suspend fun saveOrUpdateReposList(repos: List<GithubReposListModel>) = githubDao.insertOrUpdateReposList(repos)
    suspend fun saveOrUpdateRepoDetails(repos: GithubReposListModel) = githubDao.insertOrUpdateRepoDetails(repos)
    suspend fun saveOrUpdateIssue(issue: IssuesModel) = githubDao.insertOrUpdateIssue(issue)

    suspend fun updateReposList(repos: List<GithubReposListModel>) = githubDao.updateReposList(repos)
    suspend fun updateRepoDetails(repos: GithubReposListModel) = githubDao.updateRepoDetails(repos)
    suspend fun updateIssue(issue: IssuesModel) = githubDao.updateIssue(issue)
}