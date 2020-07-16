package com.fleming.tiket.ui.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel : ViewModel() {

    protected val compositeDisposable = CompositeDisposable()

    public override fun onCleared() {
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.clear()
        }
    }

}
