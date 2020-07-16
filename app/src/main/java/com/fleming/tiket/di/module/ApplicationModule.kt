package com.fleming.tiket.di.module

import android.content.Context
import com.fleming.tiket.BaseApplication
import com.fleming.tiket.base.scheduler.BaseSchedulerProvider
import com.fleming.tiket.base.scheduler.SchedulerProvider
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Module(includes = [AndroidInjectionModule::class, ViewModelModule::class])
class ApplicationModule {

    @Provides
    fun provideContext(application: BaseApplication): Context = application.applicationContext

    @Provides
    @Singleton
    fun provideScheduler(): BaseSchedulerProvider = SchedulerProvider()

}
