package com.exchange.rates.util

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager

object NetworkUtil {

    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(CONNECTIVITY_SERVICE)
        return if (connectivityManager is ConnectivityManager) {
            connectivityManager.activeNetworkInfo?.isConnected ?: false
        } else false
    }
}