package com.fleming.githubuserfinder.domain.repository

import com.fleming.githubuserfinder.domain.entity.User
import io.reactivex.Single

interface UserRepository {

    fun getUserList(keyword: String, page: Int, itemPerPage: Int): Single<List<User>>

}
