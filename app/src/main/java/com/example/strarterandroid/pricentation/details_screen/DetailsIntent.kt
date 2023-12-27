package com.example.strarterandroid.pricentation.details_screen

sealed class DetailsIntent {
    data class RepoDetails(val owner: String, val repo: String) : DetailsIntent()
}