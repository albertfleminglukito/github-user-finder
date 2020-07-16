package com.fleming.tiket.ui.main.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.fleming.tiket.domain.entity.Account
import com.fleming.tiket.ui.ext.setImageUrl
import kotlinx.android.synthetic.main.item_account.view.*

class AccountViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindData(data: Account) {
        itemView.ivAccountProfilePicture.setImageUrl(data.profilePicture)
        itemView.tvAccountUserName.text = data.username
    }

}
