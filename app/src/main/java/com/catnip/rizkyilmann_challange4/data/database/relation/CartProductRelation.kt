package com.catnip.rizkyilmann_challange4.data.database.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.catnip.rizkyilmann_challange4.data.database.entity.CartEntity
import com.catnip.rizkyilmann_challange4.data.database.entity.ProductEntity

data class CartProductRelation(
    @Embedded
    val cart: CartEntity,
    @Relation(parentColumn = "product_id", entityColumn = "id")
    val product: ProductEntity
)