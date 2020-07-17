package com.fleming.tiket.base

import com.fleming.tiket.base.scheduler.BaseSchedulerProvider
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.io.IOException
import java.lang.reflect.Type

class RxErrorHandlingCallAdapterFactory private constructor(schedulers: BaseSchedulerProvider) : CallAdapter.Factory() {

    private val _original by lazy {
        RxJava2CallAdapterFactory.createWithScheduler(schedulers.io())
    }

    override fun get(returnType: Type, annotations: Array<Annotation>, retrofit: Retrofit): CallAdapter<*, *> {
        val wrapped = _original.get(returnType, annotations, retrofit) as CallAdapter<out Any, *>
        return RxCallAdapterWrapper(retrofit, wrapped)
    }

    private inner class RxCallAdapterWrapper<R>(val _retrofit: Retrofit,
                                                val _wrappedCallAdapter: CallAdapter<R, *>) : CallAdapter<R, Any?> {

        override fun responseType(): Type = _wrappedCallAdapter.responseType()

        override fun adapt(call: Call<R>): Any? {

            return when (val adapt: Any = _wrappedCallAdapter.adapt(call)) {
                is Observable<*> -> {
                    adapt.onErrorResumeNext { throwable: Throwable -> Observable.error(asRetrofitException(throwable)) }
                }
                is Single<*> -> {
                    adapt.onErrorResumeNext { throwable -> Single.error(asRetrofitException(throwable)) }
                }
                is Completable -> {
                    adapt.onErrorResumeNext { throwable -> Completable.error(asRetrofitException(throwable)) }
                }
                else -> null
            }

        }

        private fun asRetrofitException(throwable: Throwable): RetrofitException {
            // We had non-200 http error
            if (throwable is HttpException) {
                throwable.response()?.let { response ->
                    return RetrofitException.httpError(response.raw().request.url.toString(), response, _retrofit)
                }
            }
            // A network error happened
            if (throwable is IOException) {
                return RetrofitException.networkError(throwable)
            }
            // We don't know what happened. We need to simply convert to an unknown error
            return RetrofitException.unexpectedError(throwable)
        }
    }

    companion object {
        fun create(scheduler:BaseSchedulerProvider) : CallAdapter.Factory = RxErrorHandlingCallAdapterFactory(scheduler)
    }

}