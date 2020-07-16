package com.fleming.tiket.domain.usecase

import com.fleming.tiket.domain.entity.Account
import io.reactivex.Single
import javax.inject.Inject

class GetUsersUseCase @Inject constructor() {

    fun execute(keyword: String): Single<List<Account>> {
        val list = listOf(
            Account("test", "https://stickershop.line-scdn.net/stickershop/v1/product/1100/LINEStorePC/main.png;compress=true"),
            Account("test2", "https://66.media.tumblr.com/fe2d3d509b31f0b71f37374023940d37/0496fcc7ffede8ee-4e/s1280x1920/cccf46691582add3a9346ab50c9b09f500b85494.png")
        )
        return Single.just(list)
    }

}
