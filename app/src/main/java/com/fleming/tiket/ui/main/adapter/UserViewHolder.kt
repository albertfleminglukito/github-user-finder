package com.fleming.tiket.ui.main.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.fleming.tiket.domain.entity.User
import com.fleming.tiket.ui.ext.setImageUrl
import kotlinx.android.synthetic.main.item_account.view.*

class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindData(data: User) {
        itemView.ivAccountProfilePicture.setImageUrl(data.profilePicture)
        itemView.tvAccountUserName.text = data.username
    }

}
