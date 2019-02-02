package com.mygatedemo.app.utils


import android.Manifest
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

class AlertDialogHelper(private val utils: Utils) {

    fun showDialog(context: AppCompatActivity) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        builder.setTitle("Permission Required")
            .setMessage("Give permission to access Camera/Gallery")
            .setPositiveButton(android.R.string.yes) { _, _ ->
                ActivityCompat.requestPermissions(context, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)
            }
            .setNegativeButton(
                android.R.string.no
            ) { dialog, which ->
                Toast.makeText(context, "Permission is required to upload picture", Toast.LENGTH_SHORT).show()
            }
        val alertDialog = builder.show()
    }
}