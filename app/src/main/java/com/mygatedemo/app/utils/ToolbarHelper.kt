package com.mygatedemo.app.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.mygatedemo.app.R


class ToolbarHelper {

    fun setUpToolbarNoBack(context: AppCompatActivity, title: String) {
        val toolbar = context.findViewById(R.id.toolbar_top) as Toolbar
        context.setSupportActionBar(toolbar)
        val actionBar = context.supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(true)
            actionBar.setDisplayHomeAsUpEnabled(false)
            actionBar.title = title
        }
    }

}