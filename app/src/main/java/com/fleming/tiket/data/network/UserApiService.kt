package com.fleming.tiket.data.network

import com.fleming.tiket.data.response.ListResponse
import com.fleming.tiket.data.response.UserResponse
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