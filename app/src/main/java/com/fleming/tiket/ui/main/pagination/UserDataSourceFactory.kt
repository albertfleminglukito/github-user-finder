package com.fleming.tiket.ui.main.pagination

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.fleming.tiket.base.scheduler.BaseSchedulerProvider
import com.fleming.tiket.domain.entity.User
import com.fleming.tiket.domain.usecase.GetUsersUseCase
import io.reactivex.disposables.CompositeDisposable

class UserDataSourceFactory(
    private val mGetUsersUseCase: GetUsersUseCase,
    private val mSchedulers: BaseSchedulerProvider,
    private val mCompositeDisposable: CompositeDisposable
) : DataSource.Factory<Int, User>() {

    var keyword: String = ""
    val usersLiveData = MutableLiveData<UserDataSource>()

    override fun create(): DataSource<Int, User> {
        val dataSource = UserDataSource(
            keyword,
            mGetUsersUseCase,
            mSchedulers,
            mCompositeDisposable
        )
        usersLiveData.postValue(dataSource)
        return dataSource
    }
}