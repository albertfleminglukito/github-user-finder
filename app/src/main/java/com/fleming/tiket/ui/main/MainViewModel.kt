package com.fleming.tiket.ui.main

import androidx.lifecycle.MutableLiveData
import com.fleming.tiket.base.SingleLiveEvent
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

    val accounts = MutableLiveData<List<Account>>()
    val loadingState = MutableLiveData<Boolean>()
    val showMessage = SingleLiveEvent<Any>()

    init {
        loadUsers()
    }

    fun loadUsers(keyword: String = "") {
        mGetUsersUseCase.execute(keyword)
            .doOnSubscribe { loadingState.postValue(true) }
            .doAfterSuccess { loadingState.postValue(false) }
            .subscribeOn(mSchedulers.io())
            .observeOn(mSchedulers.ui())
            .subscribe( {
                if (it.isEmpty()) showMessage.call()
                else accounts.value = it
            }, {
                showMessage.call()
            })
            .addTo(compositeDisposable)
    }

}
