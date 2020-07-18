package com.fleming.githubuserfinder.ui.main.pagination

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.fleming.githubuserfinder.Constants
import com.fleming.githubuserfinder.base.scheduler.BaseSchedulerProvider
import com.fleming.githubuserfinder.domain.entity.User
import com.fleming.githubuserfinder.domain.usecase.GetUsersUseCase
import io.reactivex.disposables.CompositeDisposable

class UserPagination(
    mGetUsersUseCase: GetUsersUseCase,
    mSchedulers: BaseSchedulerProvider,
    mCompositeDisposable: CompositeDisposable
) {
    @VisibleForTesting
    internal var dataSourceFactory: UserDataSourceFactory =
        UserDataSourceFactory(mGetUsersUseCase, mSchedulers, mCompositeDisposable)

    fun getDataSource(): LiveData<PagedList<User>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPrefetchDistance(2)
            .setPageSize(Constants.ITEM_PER_PAGE)
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

    fun getErrorState(): LiveData<Int> {
        return Transformations.switchMap(dataSourceFactory.usersLiveData) {
            it.showErrorState
        }
    }

}
