package com.mygatedemo.app.activities

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mygatedemo.app.R
import com.mygatedemo.app.data.User
import com.mygatedemo.app.database.UserDao
import com.mygatedemo.app.utils.AlertDialogHelper
import com.mygatedemo.app.utils.Utils
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import pl.aprilapps.easyphotopicker.DefaultCallback
import pl.aprilapps.easyphotopicker.EasyImage
import java.io.File
import java.util.*

class MainActivity : AppCompatActivity(), LifecycleOwner {

    private val utils: Utils by inject()
    private val alertDialogHelper: AlertDialogHelper by inject()
    private lateinit var mAdapter: UserListAdapter
    private val userDao: UserDao by inject()
    private val mainActivityViewModel: MainActivityViewModel by inject()
    var count = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fab_open_camera.setOnClickListener {
            if (utils.checkPermission())
                EasyImage.openChooserWithGallery(this, "Select Your Image", 0)
            else
                alertDialogHelper.showDialog(this)
        }
        mainActivityViewModel.getAllUsersFromDb()
        mainActivityViewModel.userResult().observe(this, Observer<List<User>> { list ->
            count = list.size
            if (list.isNotEmpty())
                setUpRecyclerView(list)
            else
                Toast.makeText(this, "No Users Exist Currently", Toast.LENGTH_SHORT).show()
        })
    }


    override fun onResume() {
        mainActivityViewModel.getAllUsersFromDb()
        super.onResume()
    }

    override fun onRequestPermissionsResult(requestCode: Int, @NonNull permissions: Array<String>, @NonNull grantResults: IntArray) {
        if (requestCode == 1) {
            if ((permissions[0] == Manifest.permission.WRITE_EXTERNAL_STORAGE && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                startGallery()
            } else
                Toast.makeText(this, "Not Granted", Toast.LENGTH_SHORT).show()
        }
    }

    private fun startGallery() {
        EasyImage.openChooserWithGallery(this, "Select", 0)
    }


    private fun setUpRecyclerView(list: List<User>) {
        mAdapter = UserListAdapter(list.toMutableList()) {

        }
        recyclerview_eventlist.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerview_eventlist.adapter = mAdapter

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        EasyImage.handleActivityResult(requestCode, resultCode, data, this, object : DefaultCallback() {
            override fun onImagesPicked(imageFiles: MutableList<File>, source: EasyImage.ImageSource?, type: Int) {
                val randomNumber = (1000..10000).random()
                val user = User()
                user.id_ = UUID.randomUUID().toString()
                user.userName = "User " + (count + 1)
                user.randomNumber = randomNumber
                user.imagePath = imageFiles[0].absolutePath
                userDao.insertUser(user)
            }

            override fun onImagePickerError(e: Exception, source: EasyImage.ImageSource, type: Int) {
                e.printStackTrace()
            }
        })
    }
}
