package com.fleming.tiket.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.fleming.tiket.R
import com.fleming.tiket.domain.entity.Account

class AccountListAdapter : ListAdapter<Account, AccountViewHolder>(AccountDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountViewHolder {
        return AccountViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_account, parent, false))
    }

    override fun onBindViewHolder(holder: AccountViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bindData(it)
        }
    }

    class AccountDiffUtil: DiffUtil.ItemCallback<Account>() {
        override fun areItemsTheSame(oldItem: Account, newItem: Account): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Account, newItem: Account): Boolean {
            return oldItem.username == newItem.username
        }
    }

}
