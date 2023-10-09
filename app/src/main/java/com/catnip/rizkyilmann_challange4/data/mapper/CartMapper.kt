package com.catnip.rizkyilmann_challange4.data.mapper

import com.catnip.rizkyilmann_challange4.data.database.entity.CartEntity
import com.catnip.rizkyilmann_challange4.data.database.relation.CartProductRelation
import com.catnip.rizkyilmann_challange4.model.Cart
import com.catnip.rizkyilmann_challange4.model.CartProduct

fun CartEntity?.toCart() = Cart(
    id = this?.id ?: 0,
    productId = this?.productId ?: 0,
    itemQuantity = this?.quantity ?: 0,
    itemNotes = this?.notes.orEmpty()
)

fun Cart?.toCartEntity() = CartEntity(
    id = this?.id,
    productId = this?.productId ?: 0,
    quantity = this?.itemQuantity ?: 0,
    notes = this?.itemNotes.orEmpty()
)

fun List<CartEntity?>.toCartList() = this.map { it.toCart() }

fun CartProductRelation.toCartProduct() = CartProduct(
    cart    = this.cart.toCart(),
    product = this.product.toProduct()
)

fun List<CartProductRelation>.toCartProductList() = this.map { it.toCartProduct() }