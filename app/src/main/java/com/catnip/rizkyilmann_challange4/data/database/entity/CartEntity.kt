package com.catnip.rizkyilmann_challange4.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "carts")
data class CartEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo(name = "product_id")
    var productId : Int = 0,
    @ColumnInfo(name = "item_quantity")
    var quantity: Int = 0,
    @ColumnInfo(name = "item_notes")
    var notes: String? = null,
)