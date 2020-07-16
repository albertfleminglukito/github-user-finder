package com.fleming.tiket.data

import com.fleming.tiket.domain.entity.User
import io.reactivex.Single
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(): UserRepository {

    override fun getUserList(keyword: String, page: Int): Single<List<User>> {
        val list = listOf(
            User("test", "https://stickershop.line-scdn.net/stickershop/v1/product/1100/LINEStorePC/main.png;compress=true"),
            User("test2", "https://66.media.tumblr.com/fe2d3d509b31f0b71f37374023940d37/0496fcc7ffede8ee-4e/s1280x1920/cccf46691582add3a9346ab50c9b09f500b85494.png"),
            User("test3", "https://stickershop.line-scdn.net/stickershop/v1/product/1100/LINEStorePC/main.png;compress=true"),
            User("test42", "https://66.media.tumblr.com/fe2d3d509b31f0b71f37374023940d37/0496fcc7ffede8ee-4e/s1280x1920/cccf46691582add3a9346ab50c9b09f500b85494.png"),
            User("test5", "https://stickershop.line-scdn.net/stickershop/v1/product/1100/LINEStorePC/main.png;compress=true"),
            User("test62", "https://66.media.tumblr.com/fe2d3d509b31f0b71f37374023940d37/0496fcc7ffede8ee-4e/s1280x1920/cccf46691582add3a9346ab50c9b09f500b85494.png"),
            User("test7", "https://stickershop.line-scdn.net/stickershop/v1/product/1100/LINEStorePC/main.png;compress=true"),
            User("test82", "https://66.media.tumblr.com/fe2d3d509b31f0b71f37374023940d37/0496fcc7ffede8ee-4e/s1280x1920/cccf46691582add3a9346ab50c9b09f500b85494.png"),
            User("test9", "https://stickershop.line-scdn.net/stickershop/v1/product/1100/LINEStorePC/main.png;compress=true"),
            User("test12", "https://66.media.tumblr.com/fe2d3d509b31f0b71f37374023940d37/0496fcc7ffede8ee-4e/s1280x1920/cccf46691582add3a9346ab50c9b09f500b85494.png"),
            User("test11", "https://stickershop.line-scdn.net/stickershop/v1/product/1100/LINEStorePC/main.png;compress=true"),
            User("test122", "https://66.media.tumblr.com/fe2d3d509b31f0b71f37374023940d37/0496fcc7ffede8ee-4e/s1280x1920/cccf46691582add3a9346ab50c9b09f500b85494.png"),
            User("test13", "https://stickershop.line-scdn.net/stickershop/v1/product/1100/LINEStorePC/main.png;compress=true"),
            User("test142", "https://66.media.tumblr.com/fe2d3d509b31f0b71f37374023940d37/0496fcc7ffede8ee-4e/s1280x1920/cccf46691582add3a9346ab50c9b09f500b85494.png"),
            User("test15", "https://stickershop.line-scdn.net/stickershop/v1/product/1100/LINEStorePC/main.png;compress=true"),
            User("test162", "https://66.media.tumblr.com/fe2d3d509b31f0b71f37374023940d37/0496fcc7ffede8ee-4e/s1280x1920/cccf46691582add3a9346ab50c9b09f500b85494.png"),
            User("test17", "https://stickershop.line-scdn.net/stickershop/v1/product/1100/LINEStorePC/main.png;compress=true"),
            User("test182", "https://66.media.tumblr.com/fe2d3d509b31f0b71f37374023940d37/0496fcc7ffede8ee-4e/s1280x1920/cccf46691582add3a9346ab50c9b09f500b85494.png"),
            User("test19", "https://stickershop.line-scdn.net/stickershop/v1/product/1100/LINEStorePC/main.png;compress=true"),
            User("test202", "https://66.media.tumblr.com/fe2d3d509b31f0b71f37374023940d37/0496fcc7ffede8ee-4e/s1280x1920/cccf46691582add3a9346ab50c9b09f500b85494.png"),
            User("test21", "https://stickershop.line-scdn.net/stickershop/v1/product/1100/LINEStorePC/main.png;compress=true"),
            User("test222", "https://66.media.tumblr.com/fe2d3d509b31f0b71f37374023940d37/0496fcc7ffede8ee-4e/s1280x1920/cccf46691582add3a9346ab50c9b09f500b85494.png"),
            User("test23", "https://stickershop.line-scdn.net/stickershop/v1/product/1100/LINEStorePC/main.png;compress=true"),
            User("test242", "https://66.media.tumblr.com/fe2d3d509b31f0b71f37374023940d37/0496fcc7ffede8ee-4e/s1280x1920/cccf46691582add3a9346ab50c9b09f500b85494.png"),
            User("test25", "https://stickershop.line-scdn.net/stickershop/v1/product/1100/LINEStorePC/main.png;compress=true"),
            User("test262", "https://66.media.tumblr.com/fe2d3d509b31f0b71f37374023940d37/0496fcc7ffede8ee-4e/s1280x1920/cccf46691582add3a9346ab50c9b09f500b85494.png"),
            User("test27", "https://stickershop.line-scdn.net/stickershop/v1/product/1100/LINEStorePC/main.png;compress=true"),
            User("test282", "https://66.media.tumblr.com/fe2d3d509b31f0b71f37374023940d37/0496fcc7ffede8ee-4e/s1280x1920/cccf46691582add3a9346ab50c9b09f500b85494.png"),
            User("test29", "https://stickershop.line-scdn.net/stickershop/v1/product/1100/LINEStorePC/main.png;compress=true"),
            User("test302", "https://66.media.tumblr.com/fe2d3d509b31f0b71f37374023940d37/0496fcc7ffede8ee-4e/s1280x1920/cccf46691582add3a9346ab50c9b09f500b85494.png"),
            User("test31", "https://stickershop.line-scdn.net/stickershop/v1/product/1100/LINEStorePC/main.png;compress=true"),
            User("test322", "https://66.media.tumblr.com/fe2d3d509b31f0b71f37374023940d37/0496fcc7ffede8ee-4e/s1280x1920/cccf46691582add3a9346ab50c9b09f500b85494.png"),
            User("test33", "https://stickershop.line-scdn.net/stickershop/v1/product/1100/LINEStorePC/main.png;compress=true"),
            User("test342", "https://66.media.tumblr.com/fe2d3d509b31f0b71f37374023940d37/0496fcc7ffede8ee-4e/s1280x1920/cccf46691582add3a9346ab50c9b09f500b85494.png"),
            User("test35", "https://stickershop.line-scdn.net/stickershop/v1/product/1100/LINEStorePC/main.png;compress=true"),
            User("test362", "https://66.media.tumblr.com/fe2d3d509b31f0b71f37374023940d37/0496fcc7ffede8ee-4e/s1280x1920/cccf46691582add3a9346ab50c9b09f500b85494.png"),
            User("test37", "https://stickershop.line-scdn.net/stickershop/v1/product/1100/LINEStorePC/main.png;compress=true"),
            User("test382", "https://66.media.tumblr.com/fe2d3d509b31f0b71f37374023940d37/0496fcc7ffede8ee-4e/s1280x1920/cccf46691582add3a9346ab50c9b09f500b85494.png"),
            User("test39", "https://stickershop.line-scdn.net/stickershop/v1/product/1100/LINEStorePC/main.png;compress=true"),
            User("test402", "https://66.media.tumblr.com/fe2d3d509b31f0b71f37374023940d37/0496fcc7ffede8ee-4e/s1280x1920/cccf46691582add3a9346ab50c9b09f500b85494.png")
        )
        return Single.just(list)
    }

}