package com.fleming.tiket.ui.main.pagination

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
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
class UserDataSourceFactoryTest {

    @get:Rule
    var mInstantTaskExecutorRule = InstantTaskExecutorRule()

    private val mGetUsersUseCase: GetUsersUseCase = mock()
    private val mSchedulers: BaseSchedulerProvider = TestSchedulerProvider()

    private val mUsersLiveData: Observer<UserDataSource> = mock()

    private lateinit var mDataSourceFactory: UserDataSourceFactory

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        mDataSourceFactory = spy(UserDataSourceFactory(mGetUsersUseCase, mSchedulers, mock()))

        mDataSourceFactory.usersLiveData.observeForever(mUsersLiveData)
    }

    @Test
    fun `create data source`() {
        // given
        val keyword = "test"
        mDataSourceFactory.keyword = keyword

        // when
        val dataSource = mDataSourceFactory.create()

        // then
        then(mUsersLiveData).should().onChanged(dataSource as UserDataSource)
        Assert.assertEquals(keyword, mDataSourceFactory.keyword)
        Assert.assertEquals(keyword, dataSource.keyword)
    }

}
