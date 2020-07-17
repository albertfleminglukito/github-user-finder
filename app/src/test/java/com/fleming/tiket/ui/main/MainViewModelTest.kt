package com.fleming.tiket.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.fleming.tiket.Constants
import com.fleming.tiket.base.scheduler.BaseSchedulerProvider
import com.fleming.tiket.domain.entity.User
import com.fleming.tiket.domain.usecase.GetUsersUseCase
import com.fleming.tiket.TestSchedulerProvider
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class MainViewModelTest {

    @get:Rule
    var mInstantTaskExecutorRule = InstantTaskExecutorRule()

    private val getUsersUseCase: GetUsersUseCase = mock()
    private val schedulers: BaseSchedulerProvider = TestSchedulerProvider()

    private lateinit var mViewModel: MainViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        mViewModel = spy(MainViewModel(getUsersUseCase, schedulers))
    }

    @Test
    fun `refreshSearch - make sure call pagination refresh function`() {
        // given
        mViewModel.mPagination = mock()
        // when
        mViewModel.refreshSearch()
        // then
        verify(mViewModel.mPagination).refresh("")
    }

}