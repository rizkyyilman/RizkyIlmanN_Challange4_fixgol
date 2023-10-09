package com.catnip.rizkyilmann_challange4.data.mapper

import com.catnip.rizkyilmann_challange4.data.database.entity.ProductEntity
import com.catnip.rizkyilmann_challange4.model.DetailMenu

fun ProductEntity?.toProduct() = DetailMenu(
    id = this?.id ?: 0,
    position = this?.position ?: 0,
    name = this?.name.orEmpty(),
    price = this?.price ?: 0.0,
    desc = this?.desc.orEmpty(),
    imgUrl = this?.imgUrl.orEmpty() // Ubah sesuai dengan nama kolom yang sesuai di ProductEntity
)


fun DetailMenu?.toProductEntity() = ProductEntity(
    name = this?.name.orEmpty(),
    position = this?.position ?: 0,
    price = this?.price ?: 0.0,
    desc = this?.desc.orEmpty(),
    imgUrl = this?.imgUrl.orEmpty(),
).apply {
    this@toProductEntity?.id?.let {
        this.id = this@toProductEntity.id
    }
}

fun List<ProductEntity?>.toProductList() = this.map { it.toProduct() }
fun List<DetailMenu?>.toProductEntity() = this.map { it.toProductEntity() }
