package com.fleming.tiket.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.fleming.tiket.Constants
import com.fleming.tiket.TestSchedulerProvider
import com.fleming.tiket.base.scheduler.BaseSchedulerProvider
import com.fleming.tiket.domain.entity.User
import com.fleming.tiket.domain.repository.UserRepository
import com.fleming.tiket.domain.usecase.GetUsersUseCase
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class UserRepositoryImplTest {

    @get:Rule
    var mInstantTaskExecutorRule = InstantTaskExecutorRule()

    private val userRepository: UserRepository = mock()
    private val schedulers: BaseSchedulerProvider = TestSchedulerProvider()

    private lateinit var mUseCase: GetUsersUseCase
    private lateinit var mResult: TestObserver<List<User>>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        mUseCase = GetUsersUseCase(userRepository, schedulers)
    }

    @Test
    fun `success - execute with keyword exists & result more than one`() {
        // given
        val keyword = "asd"
        val page = 1
        val itemPerPage = Constants.ITEM_PER_PAGE
        val responseList = listOf(
            User("test1", "http1"),
            User("test2", "http2")
        )
        whenever(userRepository.getUserList(keyword, page, itemPerPage))
            .thenReturn(Single.just(responseList))

        // when
        mResult = mUseCase.execute(keyword, page, itemPerPage).test()

        // then
        verify(userRepository).getUserList(keyword, page, itemPerPage)
        mResult.assertValue(responseList)
        mResult.assertNoErrors()
    }

    @Test
    fun `error - execute without keyword`() {
        // given
        val keyword = ""
        val page = 1
        val itemPerPage = Constants.ITEM_PER_PAGE

        // when
        mResult = mUseCase.execute(keyword, page, itemPerPage).test()

        // then
        mResult.assertError {
            it.message == GetUsersUseCase.ERROR_KEYWORD_EMPTY
        }
    }

    @Test
    fun `error - execute with keyword, but no result`() {
        // given
        val keyword = "asd"
        val page = 1
        val itemPerPage = Constants.ITEM_PER_PAGE
        val responseList = listOf<User>()
        whenever(userRepository.getUserList(keyword, page, itemPerPage))
            .thenReturn(Single.just(responseList))

        // when
        mResult = mUseCase.execute(keyword, page, itemPerPage).test()

        // then
        verify(userRepository).getUserList(keyword, page, itemPerPage)
        mResult.assertError {
            it.message == GetUsersUseCase.ERROR_RESULT_EMPTY
        }
    }

}
