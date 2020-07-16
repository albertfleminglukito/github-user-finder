package com.fleming.tiket.di.module

import com.fleming.tiket.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector()
    abstract fun bindMainActivity(): MainActivity

}
