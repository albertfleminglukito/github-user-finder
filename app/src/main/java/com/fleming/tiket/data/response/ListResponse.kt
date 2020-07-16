package com.fleming.tiket.data.response

import com.google.gson.annotations.SerializedName

data class ListResponse<T>(
    @SerializedName("total_count")
    val totalResults: Int,
    val items: List<T>
)
