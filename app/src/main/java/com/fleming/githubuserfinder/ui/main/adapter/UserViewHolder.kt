package com.fleming.githubuserfinder.ui.main.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.fleming.githubuserfinder.domain.entity.User
import com.fleming.githubuserfinder.ui.ext.setImageUrl
import kotlinx.android.synthetic.main.item_account.view.*

class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindData(data: User) {
        setContentDescription()
        itemView.ivUserProfilePicture.setImageUrl(data.profilePicture)
        itemView.tvUserName.text = data.username
    }

    private fun setContentDescription() {
        itemView.contentDescription = "itemUser$adapterPosition"
        itemView.contentDescription = "ivUserProfilePicture$adapterPosition"
        itemView.contentDescription = "tvUserName$adapterPosition"
    }

}
