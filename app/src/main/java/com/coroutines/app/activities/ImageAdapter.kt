package com.coroutines.app.activities

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.coroutines.app.R
import com.coroutines.app.data.model.ImageData
import com.coroutines.app.utils.ImageHelper
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_image.*


class ImageAdapter(
    private val userList: List<ImageData>,
    private val imageHelper: ImageHelper,
    private val onClick: (ImageData) -> Unit
) : RecyclerView.Adapter<ImageAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(userList[position], imageHelper)
    }

    override fun getItemCount(): Int = userList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return LayoutInflater.from(parent.context)
            .inflate(R.layout.item_image, parent, false).let {
                ViewHolder(it, onClick)
            }
    }

    class ViewHolder(override val containerView: View, private val onClick: (ImageData) -> Unit) :
        RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        fun bindData(repository: ImageData, imageHelper: ImageHelper) {
            with(repository) {
                imageHelper.loadImage(imageView, url)
                name_textView.text = place
                containerView.setOnClickListener { onClick(this) }
            }
        }
    }
}