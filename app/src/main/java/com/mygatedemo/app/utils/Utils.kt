package com.mygatedemo.app.utils

import android.content.Context
import android.view.View
import android.widget.ProgressBar
import android.Manifest.permission
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat


class Utils(private val context: Context) {



    fun hideProgress(progressBar: ProgressBar) {
        progressBar.visibility = View.GONE
    }

    fun showProgress(progressBar: ProgressBar) {
        progressBar.visibility = View.VISIBLE

    }


    fun checkPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            permission.WRITE_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
    }
}