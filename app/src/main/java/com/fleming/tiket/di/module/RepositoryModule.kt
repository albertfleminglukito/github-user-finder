package com.fleming.tiket.di.module

import com.fleming.tiket.data.UserRepository
import com.fleming.tiket.data.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindUserRepository(repository: UserRepositoryImpl): UserRepository

}