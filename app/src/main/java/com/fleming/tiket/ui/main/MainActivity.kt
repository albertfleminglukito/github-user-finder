package com.fleming.tiket.ui.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
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
        mViewModel.loadUsers()
    }
    private fun initView() {
        rvAccounts?.run {
            addItemDecoration(AccountItemDecoration(context.resources))
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = mAdapter
        }
        btnSearch?.setOnClickListener {
            mViewModel.loadUsers(etSearch.text.toString())
        }
    }

    private fun initViewModelObserver() {
        mViewModel.accounts.observe(this, Observer {
            mAdapter.submitList(it)
        })
        mViewModel.loadingState.observe(this, Observer { show ->
            if (show) loadingBar.visibility = View.VISIBLE
            else loadingBar.visibility = View.GONE
        })
        mViewModel.showMessage.observe(this, Observer {
            Toast.makeText(this, R.string.message_get_user_error, Toast.LENGTH_SHORT).show()
        })
    }

}
