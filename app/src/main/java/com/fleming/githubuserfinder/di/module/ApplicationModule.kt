package com.fleming.githubuserfinder.di.module

import android.content.Context
import com.fleming.githubuserfinder.BaseApplication
import com.fleming.githubuserfinder.base.scheduler.BaseSchedulerProvider
import com.fleming.githubuserfinder.base.scheduler.SchedulerProvider
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
