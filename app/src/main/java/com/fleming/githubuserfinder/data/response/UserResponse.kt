package com.fleming.githubuserfinder.data.response

import com.fleming.githubuserfinder.domain.entity.User
import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("login")
    val username: String?,
    @SerializedName("avatar_url")
    val profilePicture: String?
) {

    fun toEntity(): User {
        return User(
            username ?: "",
            profilePicture ?: ""
        )
    }

}
