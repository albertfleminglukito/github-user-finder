package com.fleming.tiket

import android.app.Application
import com.fleming.tiket.di.component.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class BaseApplication : Application(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any>? {
        return dispatchingAndroidInjector
    }

    override fun onCreate() {
        super.onCreate()

        DaggerApplicationComponent.builder().application(this)
            .build().inject(this)
    }

}
