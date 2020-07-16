package com.fleming.tiket.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.fleming.tiket.base.scheduler.BaseSchedulerProvider
import com.fleming.tiket.domain.entity.Account
import com.fleming.tiket.domain.usecase.GetUsersUseCase
import com.fleming.tiket.ui.TestSchedulerProvider
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

    private val accountsObserver: Observer<List<Account>> = mock()
    private val loadingStateObserver: Observer<Boolean> = mock()
    private val showMessageObserver: Observer<Any> = mock()

    private lateinit var mViewModel: MainViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        mViewModel = MainViewModel(getUsersUseCase, schedulers)

        mViewModel.accounts.observeForever(accountsObserver)
        mViewModel.loadingState.observeForever(loadingStateObserver)
        mViewModel.showMessage.observeForever(showMessageObserver)
    }

    @Test
    fun `loadUsers success`() {
        // given
        val keyword = ""
        val responseList = listOf(Account(), Account())
        whenever(getUsersUseCase.execute(keyword)).thenReturn(Single.just(responseList))

        // when
        mViewModel.loadUsers(keyword)

        // then
        argumentCaptor<Boolean> {
            then(loadingStateObserver).should(times(2)).onChanged(capture())
            Assert.assertTrue(firstValue)
            Assert.assertFalse(secondValue)
        }
        then(accountsObserver).should().onChanged(responseList)
    }

    @Test
    fun `loadUsers success but return empty list`() {
        // given
        val keyword = ""
        val responseList = listOf<Account>()
        whenever(getUsersUseCase.execute(keyword)).thenReturn(Single.just(responseList))

        // when
        mViewModel.loadUsers(keyword)

        // then
        argumentCaptor<Boolean> {
            then(loadingStateObserver).should(times(2)).onChanged(capture())
            Assert.assertTrue(firstValue)
            Assert.assertFalse(secondValue)
        }
        then(showMessageObserver).should().onChanged(null)
        then(accountsObserver).shouldHaveZeroInteractions()
    }

    @Test
    fun `loadUsers response from use case error`() {
        // given
        val keyword = ""
        val responseError = Throwable("error")
        whenever(getUsersUseCase.execute(keyword)).thenReturn(Single.error(responseError))

        // when
        mViewModel.loadUsers(keyword)

        // then
        argumentCaptor<Boolean> {
            then(loadingStateObserver).should(times(2)).onChanged(capture())
            Assert.assertTrue(firstValue)
            Assert.assertFalse(secondValue)
        }
        then(showMessageObserver).should().onChanged(null)
        then(accountsObserver).shouldHaveZeroInteractions()
    }

}