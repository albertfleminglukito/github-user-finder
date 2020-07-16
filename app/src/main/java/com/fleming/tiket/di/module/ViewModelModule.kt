package com.fleming.tiket.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fleming.tiket.di.ViewModelFactory
import com.fleming.tiket.di.scope.ViewModelKey
import com.fleming.tiket.ui.MainViewModel
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
