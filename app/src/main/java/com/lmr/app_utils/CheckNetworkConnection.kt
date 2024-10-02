package com.lmr.app_utils

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.view.View


object CheckNetworkConnection {
    fun isConnection(context: Context, view: View, toast: Boolean = true): Boolean{
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val conn = connectivityManager.activeNetworkInfo
        return if (conn != null && conn.isAvailable && conn.isConnected){
            true
        }else{
            if (toast) {
                //  showSnackBar(view,context.getString(R.string.internet_is_not_available))
            }

            false
        }
    }
    @SuppressLint("ObsoleteSdkInt")
    fun isNetworkAvailable(context: Context, view: View, toast: Boolean = true): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val nw      = connectivityManager.activeNetwork ?: return false
            val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
            return when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        } else {
            return connectivityManager.activeNetworkInfo?.isConnected ?: false
           // showSnackBar(view,context.getString(R.string.internet_is_not_available))
        }
    }
}