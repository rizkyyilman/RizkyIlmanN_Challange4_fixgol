package com.catnip.rizkyilmann_challange4.network.api.model.product

import androidx.annotation.Keep
import com.catnip.rizkyilmann_challange4.model.DetailMenu
import com.google.gson.annotations.SerializedName

@Keep
data class ProductItemResponse(
    @SerializedName("desc")
    val desc: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("position")
    val position: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("price")
    val price: Double?,
    @SerializedName("product_img_url")
    val imgUrl: String?,
    @SerializedName("rating")
    val rating: Double?,
    @SerializedName("supplier_name")
    val supplierName: String?,
    @SerializedName("weight_in_kg")
    val weightInKg: Double?
)


// masih bingung


fun ProductItemResponse.toProduct() = this.position?.let {
    DetailMenu(
        id = this.id,
        name = this.name.orEmpty(),
        harga = this.price ?: 0.0,
        desc = this.desc.orEmpty(),
        imgUrl = this.imgUrl.orEmpty(),
        position = it
    )
}

fun Collection<ProductItemResponse>.toProductList() = this.map { it.toProduct() }