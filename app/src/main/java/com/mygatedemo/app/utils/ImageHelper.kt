package com.mygatedemo.app.utils

import android.content.Context
import android.widget.ImageView
import androidx.annotation.NonNull
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy


class ImageHelper(private val context: Context) {


    fun loadImage(imageView: ImageView, @NonNull imageUrl: String) {
        Glide.with(context)
            .load(imageUrl)
            .dontAnimate().diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(imageView)

    }

}
