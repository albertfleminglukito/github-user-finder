package com.fleming.tiket.domain.repository

import com.fleming.tiket.domain.entity.User
import io.reactivex.Single

interface UserRepository {

    fun getUserList(keyword: String, page: Int): Single<List<User>>

}
