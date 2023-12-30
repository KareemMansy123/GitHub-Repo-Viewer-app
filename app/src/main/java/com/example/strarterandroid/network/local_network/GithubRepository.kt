package com.example.strarterandroid.network.local_network

import com.example.strarterandroid.core.model.GithubReposListModel
import com.example.strarterandroid.core.model.IssuesModel

class GithubRepository(private val githubDao: GithubDao) : IGithubRepository {
    override suspend fun saveOrUpdateReposList(repos: List<GithubReposListModel>) = githubDao.insertOrUpdateReposList(repos)
    override suspend fun getReposFromDb(): List<GithubReposListModel> = githubDao.getAllRepos()
    override suspend fun getRepoByOwnerAndName(owner: String, repo: String): GithubReposListModel? = githubDao.getRepoByOwnerAndName(owner, repo)
    override suspend fun saveOrUpdateIssue(issue: List<IssuesModel>) = githubDao.insertOrUpdateIssue(issue)
    override suspend fun getIssuesFromDb(): List<IssuesModel> = githubDao.getAllIssues()
}
