package com.example.strarterandroid.network.local_network

import com.example.strarterandroid.network.model.GithubReposListModel
import com.example.strarterandroid.network.model.IssuesModel

class GithubRepository(private val githubDao: GithubDao) {
    suspend fun saveOrUpdateReposList(repos: List<GithubReposListModel>) = githubDao.insertOrUpdateReposList(repos)
    suspend fun getReposFromDb(): List<GithubReposListModel> {
        return githubDao.getAllRepos()
    }
    suspend fun getRepoByOwnerAndName(owner: String, repo: String): GithubReposListModel? {
        return githubDao.getRepoByOwnerAndName(owner, repo)
    }
    suspend fun saveOrUpdateIssue(issue: List<IssuesModel>) = githubDao.insertOrUpdateIssue(issue)
    suspend fun getIssuesFromDb(): List<IssuesModel> {
        return githubDao.getAllIssues()
    }
    suspend fun updateReposList(repos: List<GithubReposListModel>) = githubDao.updateReposList(repos)
    suspend fun updateIssue(issue: List<IssuesModel>) = githubDao.updateIssue(issue)
}