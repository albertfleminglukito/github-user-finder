package com.fleming.tiket.ui

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.fleming.tiket.R
import com.fleming.tiket.ui.base.BaseActivity

class MainActivity : BaseActivity(R.layout.activity_main) {

    private lateinit var mViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mViewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
    }

}
