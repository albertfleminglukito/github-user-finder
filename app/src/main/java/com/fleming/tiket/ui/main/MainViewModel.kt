package com.fleming.tiket.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fleming.tiket.base.scheduler.BaseSchedulerProvider
import com.fleming.tiket.domain.entity.Account
import com.fleming.tiket.domain.usecase.GetUsersUseCase
import com.fleming.tiket.ui.base.BaseViewModel
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val mGetUsersUseCase: GetUsersUseCase,
    private val mSchedulers: BaseSchedulerProvider
): BaseViewModel() {

    private val mAccounts = MutableLiveData<List<Account>>()
    val accounts: LiveData<List<Account>> = mAccounts

    init {
        loadUsers()
    }

    fun loadUsers() {
        mGetUsersUseCase.execute()
            .subscribeOn(mSchedulers.io())
            .observeOn(mSchedulers.ui())
            .subscribe( {
                mAccounts.value = it
            }, {

            })
            .addTo(compositeDisposable)
    }


}