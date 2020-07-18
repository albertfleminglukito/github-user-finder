package com.fleming.githubuserfinder.di.module

import com.fleming.githubuserfinder.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector()
    abstract fun bindMainActivity(): MainActivity

}
