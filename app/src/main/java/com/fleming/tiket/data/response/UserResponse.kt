package com.fleming.tiket.data.response

import com.fleming.tiket.domain.entity.User
import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("login")
    val userName: String?,
    @SerializedName("avatar_url")
    val profilePicture: String?
) {

    fun toEntity(): User {
        return User(
            userName ?: "",
            profilePicture ?: ""
        )
    }

}
