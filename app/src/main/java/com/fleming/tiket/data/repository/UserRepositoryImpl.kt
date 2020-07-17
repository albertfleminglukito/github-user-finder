package com.fleming.tiket.data.repository

import com.fleming.tiket.data.network.UserApiService
import com.fleming.tiket.domain.entity.User
import com.fleming.tiket.domain.repository.UserRepository
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
