package com.example.strarterandroid.network.local_network

import com.example.strarterandroid.core.model.GithubReposListModel
import com.example.strarterandroid.core.model.IssuesModel

interface IGithubRepository {
    suspend fun saveOrUpdateReposList(repos: List<GithubReposListModel>)
    suspend fun getReposFromDb(): List<GithubReposListModel>
    suspend fun getRepoByOwnerAndName(owner: String, repo: String): GithubReposListModel?
    suspend fun saveOrUpdateIssue(issue: List<IssuesModel>)
    suspend fun getIssuesFromDb(): List<IssuesModel>
}
