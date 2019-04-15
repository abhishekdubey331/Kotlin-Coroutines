package com.mygatedemo.app.utils

import android.content.Context
import android.net.ConnectivityManager
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import okhttp3.ResponseBody
import org.json.JSONObject


class Utils(private val context: Context) {

    fun hideProgress(progressBar: ProgressBar) {
        progressBar.visibility = View.GONE
    }

    fun showProgress(progressBar: ProgressBar) {
        progressBar.visibility = View.VISIBLE
    }


    fun showMessage(message: String) {
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
    }

    fun verifyAvailableNetwork(activity: AppCompatActivity):Boolean{
        val connectivityManager=activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo=connectivityManager.activeNetworkInfo
        return  networkInfo!=null && networkInfo.isConnected
    }

    fun getErrorMessage(e: ResponseBody): String {
        val error: String
        val errorBody = e.string()
        try {
            val json = JSONObject(errorBody)
            error = json.getString("message")
        } catch (e: Exception) {
            return "Something went wrong"
        }
        return error
    }
}