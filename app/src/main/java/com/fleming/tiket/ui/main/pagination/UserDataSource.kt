package com.fleming.tiket.ui.main.pagination

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.fleming.tiket.base.scheduler.BaseSchedulerProvider
import com.fleming.tiket.domain.entity.User
import com.fleming.tiket.domain.usecase.GetUsersUseCase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo

class UserDataSource(
    private val mKeyword: String,
    private val mGetUsersUseCase: GetUsersUseCase,
    private val mSchedulers: BaseSchedulerProvider,
    private val mCompositeDisposable: CompositeDisposable
) : PageKeyedDataSource<Int, User>() {

    val showLoadingState = MutableLiveData<Boolean>()
    val showErrorState = MutableLiveData<Throwable>()

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, User>
    ) {
        mGetUsersUseCase.execute(mKeyword, 1)
            .doOnSubscribe { showLoadingState.postValue(true) }
            .subscribeOn(mSchedulers.io())
            .observeOn(mSchedulers.ui())
            .doAfterTerminate { showLoadingState.value = false }
            .subscribe({
                callback.onResult(it, 0, it.size, 0, 2)
            }, {
                showErrorState.postValue(it)
            })
            .addTo(mCompositeDisposable)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, User>) {
        val page = params.key + 1
        mGetUsersUseCase.execute(mKeyword, page)
            .doOnSubscribe { showLoadingState.postValue(true) }
            .subscribeOn(mSchedulers.io())
            .observeOn(mSchedulers.ui())
            .doAfterTerminate { showLoadingState.value = false }
            .subscribe({
                callback.onResult(it, page)
            }, {
                showErrorState.postValue(it)
            })
            .addTo(mCompositeDisposable)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, User>) {}

}
