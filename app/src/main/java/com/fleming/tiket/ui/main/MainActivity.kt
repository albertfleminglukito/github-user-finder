package com.fleming.tiket.ui.main

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.fleming.tiket.R
import com.fleming.tiket.ui.base.BaseActivity
import com.fleming.tiket.ui.main.adapter.AccountItemDecoration
import com.fleming.tiket.ui.main.adapter.AccountListAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(R.layout.activity_main) {

    private lateinit var mViewModel: MainViewModel
    private val mAdapter: AccountListAdapter by lazy { AccountListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()

        mViewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        initViewModelObserver()
    }

    private fun initViewModelObserver() {
        mViewModel.accounts.observe(this, Observer {
            mAdapter.submitList(it)
        })
    }

    private fun initView() {
        rvAccounts?.run {
            addItemDecoration(AccountItemDecoration(context.resources))
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = mAdapter
        }
        btnSearch?.setOnClickListener {
            mViewModel.loadUsers()
        }
    }

}
