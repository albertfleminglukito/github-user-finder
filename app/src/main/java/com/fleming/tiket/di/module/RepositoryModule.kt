package com.fleming.tiket.di.module

import com.fleming.tiket.data.repository.UserRepositoryImpl
import com.fleming.tiket.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindUserRepository(repository: UserRepositoryImpl): UserRepository

}