package com.fleming.githubuserfinder.ui.main.pagination

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.fleming.githubuserfinder.base.scheduler.BaseSchedulerProvider
import com.fleming.githubuserfinder.domain.entity.User
import com.fleming.githubuserfinder.domain.usecase.GetUsersUseCase
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
