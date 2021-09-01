package com.example.pokedex.utils

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build

fun thereIsInternetConnection(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE)
            as ConnectivityManager
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val netWork = connectivityManager.activeNetwork
        netWork != null
    } else {
        val netWorkInfo = connectivityManager.activeNetworkInfo
        netWorkInfo != null && netWorkInfo.isConnected
    }
}