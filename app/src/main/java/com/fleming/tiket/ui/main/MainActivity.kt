package com.fleming.tiket.ui.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.fleming.tiket.R
import com.fleming.tiket.ui.base.BaseActivity
import com.fleming.tiket.ui.main.adapter.UserItemDecoration
import com.fleming.tiket.ui.main.adapter.UserListAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(R.layout.activity_main) {

    private lateinit var mViewModel: MainViewModel
    private val mAdapter: UserListAdapter by lazy { UserListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()

        mViewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        initViewModelObserver()
    }
    private fun initView() {
        rvAccounts?.run {
            addItemDecoration(UserItemDecoration(context.resources))
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = mAdapter
        }
        btnSearch?.setOnClickListener {
            mViewModel.refreshSearch(etSearch.text.toString())
        }
    }

    private fun initViewModelObserver() {
        mViewModel.users.observe(this, Observer {
            mAdapter.submitList(it)
        })
        mViewModel.loadingState.observe(this, Observer { show ->
            if (show) loadingBar.visibility = View.VISIBLE
            else loadingBar.visibility = View.GONE
        })
        mViewModel.errorState.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }

}
