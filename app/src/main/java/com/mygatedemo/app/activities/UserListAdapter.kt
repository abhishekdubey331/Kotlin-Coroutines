package com.mygatedemo.app.activities

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mygatedemo.app.R
import com.mygatedemo.app.data.User
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_user.*


class UserListAdapter(
    private val userList: MutableList<User>,
    private val onClick: (User) -> Unit
) : RecyclerView.Adapter<UserListAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(userList[position])
    }

    override fun getItemCount(): Int = userList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return LayoutInflater.from(parent.context)
            .inflate(R.layout.item_user, parent, false).let {
                ViewHolder(it, onClick)
            }
    }

    class ViewHolder(override val containerView: View, private val onClick: (User) -> Unit) :
        RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        fun bindData(repository: User) {
            with(repository) {
                tv_user_name.text = userName
                tv_random.text = randomNumber.toString()
                val myBitmap = BitmapFactory.decodeFile(imagePath)
                userImage.setImageBitmap(myBitmap)
                containerView.setOnClickListener { onClick(this) }
            }
        }
    }
}