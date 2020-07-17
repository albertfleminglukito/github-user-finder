package com.fleming.tiket.ui.main.pagination

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import com.fleming.tiket.base.scheduler.BaseSchedulerProvider
import com.fleming.tiket.domain.usecase.GetUsersUseCase
import com.fleming.tiket.TestSchedulerProvider
import com.nhaarman.mockitokotlin2.*
import io.reactivex.disposables.CompositeDisposable
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class UserPaginationTest {

    @get:Rule
    var mInstantTaskExecutorRule = InstantTaskExecutorRule()

    private val mGetUsersUseCase: GetUsersUseCase = mock()
    private val mSchedulers: BaseSchedulerProvider = TestSchedulerProvider()

    private lateinit var mPagination: UserPagination

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        mPagination = spy(UserPagination(mGetUsersUseCase, mSchedulers, mock()))
    }

    @Test
    fun `refresh - make sure keyword is set`() {
        // given
        val keyword = "test"
        // when
        mPagination.refresh(keyword)
        // then
        Assert.assertEquals(keyword, mPagination.dataSourceFactory.keyword)
    }

}
