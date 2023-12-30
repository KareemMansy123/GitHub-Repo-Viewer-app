package com.example.strarterandroid.di

enum class ApiConfig(val url: String) {
    BASE_URL("https://api.github.com")
}

enum class DatabaseConfig(val dbName: String) {
    DB_NAME("github_database")
}