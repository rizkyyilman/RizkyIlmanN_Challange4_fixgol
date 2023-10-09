package com.catnip.rizkyilmann_challange4.model

data class Cart(
    var id: Int = 0,
    var productId : Int = 0,
    var itemQuantity: Int = 0,
    var itemNotes: String? = null,
)