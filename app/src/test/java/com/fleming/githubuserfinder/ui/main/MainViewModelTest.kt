package com.fleming.githubuserfinder.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.fleming.githubuserfinder.base.scheduler.BaseSchedulerProvider
import com.fleming.githubuserfinder.domain.usecase.GetUsersUseCase
import com.fleming.githubuserfinder.TestSchedulerProvider
import com.nhaarman.mockitokotlin2.*
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