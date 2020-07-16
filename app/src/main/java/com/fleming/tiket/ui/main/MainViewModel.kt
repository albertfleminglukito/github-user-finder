package com.fleming.tiket.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.fleming.tiket.base.SingleLiveEvent
import com.fleming.tiket.base.scheduler.BaseSchedulerProvider
import com.fleming.tiket.domain.entity.User
import com.fleming.tiket.domain.usecase.GetUsersUseCase
import com.fleming.tiket.ui.base.BaseViewModel
import com.fleming.tiket.ui.main.pagination.UserPagination
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val mGetUsersUseCase: GetUsersUseCase,
    private val mSchedulers: BaseSchedulerProvider
): BaseViewModel() {

    private var mPagination = UserPagination(mGetUsersUseCase, mSchedulers, compositeDisposable)
    val users: LiveData<PagedList<User>> = mPagination.getDataSource()
    val loadingState: LiveData<Boolean> = mPagination.getLoadingState()
    val errorState: LiveData<Throwable> = mPagination.getErrorState()

    fun refreshSearch(keyword: String = "") {
        mPagination.refresh(keyword)
    }

//    fun loadUsers(keyword: String = "", page: Int = 0) {
//        mGetUsersUseCase.execute(keyword, page)
//            .doOnSubscribe { loadingState.postValue(true) }
//            .subscribeOn(mSchedulers.io())
//            .observeOn(mSchedulers.ui())
//            .doAfterTerminate { loadingState.value = false }
//            .subscribe( {
//                if (it.isEmpty()) showMessage.call()
//                else users.value = it
//            }, {
//                showMessage.call()
//            })
//            .addTo(compositeDisposable)
//    }

}
