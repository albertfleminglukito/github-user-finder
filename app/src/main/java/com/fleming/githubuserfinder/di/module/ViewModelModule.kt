package com.fleming.githubuserfinder.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fleming.githubuserfinder.di.ViewModelFactory
import com.fleming.githubuserfinder.di.scope.ViewModelKey
import com.fleming.githubuserfinder.ui.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {

	@Binds
	abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

	@Binds
	@IntoMap
	@ViewModelKey(MainViewModel::class)
	abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel

}
