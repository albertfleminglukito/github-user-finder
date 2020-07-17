package com.fleming.tiket.ui.main.pagination

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.fleming.tiket.Constants
import com.fleming.tiket.R
import com.fleming.tiket.base.SingleLiveEvent
import com.fleming.tiket.base.scheduler.BaseSchedulerProvider
import com.fleming.tiket.domain.entity.User
import com.fleming.tiket.domain.usecase.GetUsersUseCase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo

class UserDataSource(
    @VisibleForTesting internal val keyword: String,
    private val mGetUsersUseCase: GetUsersUseCase,
    private val mSchedulers: BaseSchedulerProvider,
    private val mCompositeDisposable: CompositeDisposable
) : PageKeyedDataSource<Int, User>() {

    val showLoadingState = MutableLiveData<Boolean>()
    val showErrorState = SingleLiveEvent<Int>()

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, User>
    ) {
        mGetUsersUseCase.execute(keyword, 1, Constants.ITEM_PER_PAGE)
            .doOnSubscribe { showLoadingState.postValue(true) }
            .subscribeOn(mSchedulers.io())
            .observeOn(mSchedulers.ui())
            .doAfterTerminate { showLoadingState.value = false }
            .subscribe({
                callback.onResult(it, 0, it.size, 0, 2)
            }, {
                showErrorState.value = getErrorMessageResource(it)
            })
            .addTo(mCompositeDisposable)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, User>) {
        val page = params.key + 1
        mGetUsersUseCase.execute(keyword, page, Constants.ITEM_PER_PAGE)
            .doOnSubscribe { showLoadingState.postValue(true) }
            .subscribeOn(mSchedulers.io())
            .observeOn(mSchedulers.ui())
            .doAfterTerminate { showLoadingState.value = false }
            .subscribe({
                callback.onResult(it, page)
            }, {
                showErrorState.value = getErrorMessageResource(it)
            })
            .addTo(mCompositeDisposable)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, User>) {}

    @VisibleForTesting
    internal fun getErrorMessageResource(throwable: Throwable): Int {
        return when (throwable.message) {
            GetUsersUseCase.ERROR_KEYWORD_EMPTY -> R.string.message_get_user_keyword_empty_error
            GetUsersUseCase.ERROR_RESULT_EMPTY -> R.string.message_get_user_empty_result_error
            else -> R.string.message_get_user_error
        }
    }

}
