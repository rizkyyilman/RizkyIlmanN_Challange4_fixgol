package com.catnip.rizkyilmann_challange4.model
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
data class DetailMenu(
    val id: Int? = null,
    val position: Int,
    val name: String,
    val harga: Double,
    val imgUrl: String,
    val desc: String
) : Parcelable