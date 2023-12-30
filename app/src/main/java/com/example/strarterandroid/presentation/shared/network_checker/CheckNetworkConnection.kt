package com.example.strarterandroid.presentation.shared.network_checker

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

class RealNetworkChecker(private val context: Context) : NetworkChecker {
    override fun isNetworkAvailable(): Boolean {
        return (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).run {
            getNetworkCapabilities(activeNetwork)?.run {
                hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                        || hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                        || hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
            } ?: false
        }
    }
}

