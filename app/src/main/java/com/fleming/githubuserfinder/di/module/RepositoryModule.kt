package com.fleming.githubuserfinder.di.module

import com.fleming.githubuserfinder.data.repository.UserRepositoryImpl
import com.fleming.githubuserfinder.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindUserRepository(repository: UserRepositoryImpl): UserRepository

}