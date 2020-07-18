package com.fleming.githubuserfinder.ui.main.pagination

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.fleming.githubuserfinder.base.scheduler.BaseSchedulerProvider
import com.fleming.githubuserfinder.domain.usecase.GetUsersUseCase
import com.fleming.githubuserfinder.TestSchedulerProvider
import com.nhaarman.mockitokotlin2.*
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
