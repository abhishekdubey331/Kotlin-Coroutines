package com.mygatedemo.app.utils

import android.content.Context
import android.view.View
import android.widget.ProgressBar
import android.Manifest.permission
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat


class Utils(private val context: Context) {

    fun hideProgress(progressBar: ProgressBar) {
        progressBar.visibility = View.GONE
    }

    fun showProgress(progressBar: ProgressBar) {
        progressBar.visibility = View.VISIBLE

    }

    fun verifyAvailableNetwork(activity: AppCompatActivity):Boolean{
        val connectivityManager=activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo=connectivityManager.activeNetworkInfo
        return  networkInfo!=null && networkInfo.isConnected
    }
}