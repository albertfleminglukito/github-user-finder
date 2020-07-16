package com.fleming.tiket.domain.usecase

import com.fleming.tiket.base.scheduler.BaseSchedulerProvider
import com.fleming.tiket.data.UserRepository
import com.fleming.tiket.domain.entity.User
import io.reactivex.Single
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val mSchedulers: BaseSchedulerProvider
) {

    fun execute(keyword: String, page: Int): Single<List<User>> {
        return userRepository.getUserList(keyword, page)
            .subscribeOn(mSchedulers.io())
    }

}
