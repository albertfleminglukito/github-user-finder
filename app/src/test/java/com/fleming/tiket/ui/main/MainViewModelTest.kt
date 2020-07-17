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

//@RunWith(JUnit4::class)
//class MainViewModelTest {

//    @get:Rule
//    var mInstantTaskExecutorRule = InstantTaskExecutorRule()
//
//    private val getUsersUseCase: GetUsersUseCase = mock()
//    private val schedulers: BaseSchedulerProvider =
//        TestSchedulerProvider()
//
//    private val accountsObserver: Observer<List<User>> = mock()
//    private val loadingStateObserver: Observer<Boolean> = mock()
//    private val errorStateObserver: Observer<Any> = mock()
//
//    private lateinit var mViewModel: MainViewModel
//
//    @Before
//    fun setUp() {
//        MockitoAnnotations.initMocks(this)
//        mViewModel = MainViewModel(getUsersUseCase, schedulers)
//
//        mViewModel.users.observeForever(accountsObserver)
//        mViewModel.loadingState.observeForever(loadingStateObserver)
//        mViewModel.errorState.observeForever(errorStateObserver)
//    }
//
//    @Test
//    fun `refreshSearch success`() {
//        // given
//        val keyword = ""
//        val responseList = listOf(User(), User())
//        whenever(getUsersUseCase.execute(keyword, 1, Constants.ITEM_PER_PAGE)).thenReturn(Single.just(responseList))
//
//        // when
//        mViewModel.refreshSearch(keyword)
//
//        // then
//        argumentCaptor<Boolean> {
//            then(loadingStateObserver).should(times(2)).onChanged(capture())
//            Assert.assertTrue(firstValue)
//            Assert.assertFalse(secondValue)
//        }
//        then(accountsObserver).should().onChanged(responseList)
//    }
//
//    @Test
//    fun `refreshSearch success but return empty list`() {
//        // given
//        val keyword = ""
//        val responseList = listOf<User>()
//        whenever(getUsersUseCase.execute(keyword, 1, Constants.ITEM_PER_PAGE)).thenReturn(Single.just(responseList))
//
//        // when
//        mViewModel.refreshSearch(keyword)
//
//        // then
//        argumentCaptor<Boolean> {
//            then(loadingStateObserver).should(times(2)).onChanged(capture())
//            Assert.assertTrue(firstValue)
//            Assert.assertFalse(secondValue)
//        }
//        then(errorStateObserver).should().onChanged(null)
//        then(accountsObserver).shouldHaveZeroInteractions()
//    }
//
//    @Test
//    fun `refreshSearch response from use case error`() {
//        // given
//        val keyword = ""
//        val responseError = Throwable("error")
//        whenever(getUsersUseCase.execute(keyword, 1, Constants.ITEM_PER_PAGE)).thenReturn(Single.error(responseError))
//
//        // when
//        mViewModel.refreshSearch(keyword)
//
//        // then
//        argumentCaptor<Boolean> {
//            then(loadingStateObserver).should(times(2)).onChanged(capture())
//            Assert.assertTrue(firstValue)
//            Assert.assertFalse(secondValue)
//        }
//        then(errorStateObserver).should().onChanged(null)
//        then(accountsObserver).shouldHaveZeroInteractions()
//    }

//}