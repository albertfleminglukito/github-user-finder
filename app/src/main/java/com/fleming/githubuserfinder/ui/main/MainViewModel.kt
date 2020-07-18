package com.fleming.githubuserfinder.ui.main

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.fleming.githubuserfinder.base.scheduler.BaseSchedulerProvider
import com.fleming.githubuserfinder.domain.entity.User
import com.fleming.githubuserfinder.domain.usecase.GetUsersUseCase
import com.fleming.githubuserfinder.ui.base.BaseViewModel
import com.fleming.githubuserfinder.ui.main.pagination.UserPagination
import javax.inject.Inject

class MainViewModel @Inject constructor(
    mGetUsersUseCase: GetUsersUseCase,
    mSchedulers: BaseSchedulerProvider
): BaseViewModel() {

    @VisibleForTesting
    internal var mPagination = UserPagination(mGetUsersUseCase, mSchedulers, compositeDisposable)
    val users: LiveData<PagedList<User>> = mPagination.getDataSource()
    val loadingState: LiveData<Boolean> = mPagination.getLoadingState()
    val errorState: LiveData<Int> = mPagination.getErrorState()

    fun refreshSearch(keyword: String = "") {
        mPagination.refresh(keyword)
    }

}
