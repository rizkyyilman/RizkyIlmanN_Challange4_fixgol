package com.catnip.rizkyilmann_challange4.model
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
data class DetailMenu(
    val id: String = UUID.randomUUID().toString(),
    val position: Int,
    val name: String,
    val price: String,
    val imgUrl: String,
    val desc: String
) : Parcelable