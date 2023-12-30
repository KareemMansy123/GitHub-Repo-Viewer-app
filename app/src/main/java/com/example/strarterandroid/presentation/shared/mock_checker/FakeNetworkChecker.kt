package com.example.strarterandroid.presentation.shared.mock_checker

import com.example.strarterandroid.presentation.shared.network_checker.NetworkChecker

class FakeNetworkChecker(private var networkAvailable: Boolean) : NetworkChecker {
    override fun isNetworkAvailable(): Boolean = networkAvailable
}