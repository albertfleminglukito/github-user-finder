package com.fleming.githubuserfinder.ui.main

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.fleming.githubuserfinder.R
import com.fleming.githubuserfinder.ui.base.BaseActivity
import com.fleming.githubuserfinder.ui.custom.ErrorDialog
import com.fleming.githubuserfinder.ui.main.adapter.UserItemDecoration
import com.fleming.githubuserfinder.ui.main.adapter.UserListAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(R.layout.activity_main) {

    private lateinit var mViewModel: MainViewModel
    private val mAdapter: UserListAdapter by lazy { UserListAdapter() }
    private var mFirstLoad = true

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
            if (!mFirstLoad) {
                val dialog = ErrorDialog()
                dialog.setMessage(getString(it))
                dialog.show(supportFragmentManager, "")
            }
            mFirstLoad = false
        })
    }

}
