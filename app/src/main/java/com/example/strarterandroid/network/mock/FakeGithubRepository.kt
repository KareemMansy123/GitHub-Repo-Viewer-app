package com.example.strarterandroid.network.mock

import com.example.strarterandroid.core.model.GithubReposListModel
import com.example.strarterandroid.core.model.IssuesModel
import com.example.strarterandroid.network.local_network.IGithubRepository

class FakeGithubRepository : IGithubRepository {
    var reposList: List<GithubReposListModel> = emptyList()
    var issuesList: List<IssuesModel> = emptyList()
    var shouldReturnEmptyList = false

    override suspend fun saveOrUpdateReposList(repos: List<GithubReposListModel>) {
        reposList = repos
    }

    override suspend fun getReposFromDb(): List<GithubReposListModel> {
        return if (shouldReturnEmptyList) emptyList() else reposList
    }

    override suspend fun getRepoByOwnerAndName(owner: String, repo: String): GithubReposListModel? {
        return reposList.find { it.owner.login == owner && it.name == repo }
    }

    override suspend fun saveOrUpdateIssue(issue: List<IssuesModel>) {
        issuesList = issue
    }

    override suspend fun getIssuesFromDb(): List<IssuesModel> {
        return issuesList
    }
}

