package com.example.zivameassignment.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Utkarsh Sundaram on 28-09-2021.
 */

open class Network @Inject constructor(val context: Context) : NetworkConnectivity {
    override fun getNetworkInfo(): NetworkInfo? {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo
    }
    override fun isConnected(): Boolean {
        val info = getNetworkInfo()
        return info != null && info.isConnected
    }
}

open interface NetworkConnectivity {
    fun getNetworkInfo(): NetworkInfo?
    fun isConnected(): Boolean
}