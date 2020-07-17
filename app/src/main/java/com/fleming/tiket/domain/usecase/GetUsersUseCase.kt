package com.fleming.tiket.domain.usecase

import com.fleming.tiket.base.scheduler.BaseSchedulerProvider
import com.fleming.tiket.domain.entity.User
import com.fleming.tiket.domain.repository.UserRepository
import io.reactivex.Single
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val mSchedulers: BaseSchedulerProvider
) {

    fun execute(keyword: String, page: Int, itemPerPage: Int): Single<List<User>> {
        if (keyword.isEmpty()) {
            return Single.error(Throwable(ERROR_KEYWORD_EMPTY))
        }
        return userRepository.getUserList(keyword, page, itemPerPage)
            .filter { it.isNotEmpty() }
            .switchIfEmpty(Single.error(Throwable(ERROR_RESULT_EMPTY)))
            .subscribeOn(mSchedulers.io())
    }

    companion object {
        const val ERROR_KEYWORD_EMPTY = "ERROR_KEYWORD_EMPTY"
        const val ERROR_RESULT_EMPTY = "ERROR_RESULT_EMPTY"
    }

}
