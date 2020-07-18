package com.fleming.githubuserfinder.data.network

import com.fleming.githubuserfinder.data.response.ListResponse
import com.fleming.githubuserfinder.data.response.UserResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApiService {

    @GET("search/users")
    fun getUsers(
        @Query("q") keyword: String,
        @Query("page") page: Int,
        @Query("per_page") numberPerPage: Int
    ): Single<ListResponse<UserResponse>>

}