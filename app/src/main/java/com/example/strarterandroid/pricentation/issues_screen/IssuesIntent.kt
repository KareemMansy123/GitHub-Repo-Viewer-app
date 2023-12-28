package com.example.strarterandroid.pricentation.issues_screen

sealed class IssuesIntent {
    data class RepoIssues(val owner: String, val repo: String) : IssuesIntent()
    data class DateIssues(val date: String) : IssuesIntent()
}