package com.fleming.githubuserfinder.data.repository

import com.fleming.githubuserfinder.data.network.UserApiService
import com.fleming.githubuserfinder.domain.entity.User
import com.fleming.githubuserfinder.domain.repository.UserRepository
import io.reactivex.Single
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val mUserApiService: UserApiService
): UserRepository {

    override fun getUserList(keyword: String, page: Int, itemPerPage: Int): Single<List<User>> {
        return mUserApiService.getUsers(keyword, page, itemPerPage)
            .map { it.items.map { item -> item.toEntity() } }
    }

}
