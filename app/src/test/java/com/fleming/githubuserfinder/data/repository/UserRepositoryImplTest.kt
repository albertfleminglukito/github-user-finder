package com.fleming.githubuserfinder.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.fleming.githubuserfinder.Constants
import com.fleming.githubuserfinder.data.network.UserApiService
import com.fleming.githubuserfinder.data.response.ListResponse
import com.fleming.githubuserfinder.data.response.UserResponse
import com.fleming.githubuserfinder.domain.entity.User
import com.fleming.githubuserfinder.domain.repository.UserRepository
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

    private val apiService: UserApiService = mock()

    private lateinit var mRepository: UserRepository
    private lateinit var mResult: TestObserver<List<User>>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        mRepository = UserRepositoryImpl(apiService)
    }

    @Test
    fun `success - execute with keyword exists & result more than one`() {
        // given
        val keyword = "asd"
        val page = 1
        val itemPerPage = Constants.ITEM_PER_PAGE
        val usersResponse = listOf(
            UserResponse("test1", "http1"),
            UserResponse("test2", "http2")
        )
        val listResponse = ListResponse(usersResponse)
        whenever(apiService.getUsers(keyword, page, itemPerPage))
            .thenReturn(Single.just(listResponse))

        // when
        mResult = mRepository.getUserList(keyword, page, itemPerPage).test()

        // then
        verify(apiService).getUsers(keyword, page, itemPerPage)
        mResult.assertValue {
            it == listResponse.items.map { item -> item.toEntity() }
            it[0].username == usersResponse[0].username
            it[0].profilePicture == usersResponse[0].profilePicture
            it[1].username == usersResponse[1].username
            it[1].profilePicture == usersResponse[1].profilePicture
        }
        mResult.assertNoErrors()
    }
}