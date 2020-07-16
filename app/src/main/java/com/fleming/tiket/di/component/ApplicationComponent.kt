package com.fleming.tiket.di.component

import com.fleming.tiket.BaseApplication
import com.fleming.tiket.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ActivityBuilderModule::class,
    ApplicationModule::class,
    NetworkModule::class,
    RepositoryModule::class,
    ViewModelModule::class
])
interface ApplicationComponent {

    fun inject(baseApplication: BaseApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: BaseApplication): Builder
        fun build(): ApplicationComponent
    }

}
