package com.catnip.rizkyilmann_challange4.network.api.model.category

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class CategoriesResponse(
    @SerializedName("code")
    val code: Int?,
    @SerializedName("data")
    val data: List<CategoryResponse>?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("status")
    val status: Boolean?
)
