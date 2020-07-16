package com.fleming.tiket.ui.main.pagination

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.fleming.tiket.base.scheduler.BaseSchedulerProvider
import com.fleming.tiket.domain.entity.User
import com.fleming.tiket.domain.usecase.GetUsersUseCase
import io.reactivex.disposables.CompositeDisposable

class UserPagination(
    mGetUsersUseCase: GetUsersUseCase,
    mSchedulers: BaseSchedulerProvider,
    mCompositeDisposable: CompositeDisposable
) {
    private var dataSourceFactory: UserDataSourceFactory =
        UserDataSourceFactory(mGetUsersUseCase, mSchedulers, mCompositeDisposable)

    fun getDataSource(): LiveData<PagedList<User>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPrefetchDistance(2)
            .setPageSize(100)
            .build()

        return LivePagedListBuilder(dataSourceFactory, config).build()
    }

    fun refresh(keyword: String) {
        dataSourceFactory.keyword = keyword
        dataSourceFactory.usersLiveData.value?.invalidate()
    }

    fun getLoadingState(): LiveData<Boolean> {
        return Transformations.switchMap(dataSourceFactory.usersLiveData) {
            it.showLoadingState
        }
    }

    fun getErrorState(): LiveData<Throwable> {
        return Transformations.switchMap(dataSourceFactory.usersLiveData) {
            it.showErrorState
        }
    }

}
