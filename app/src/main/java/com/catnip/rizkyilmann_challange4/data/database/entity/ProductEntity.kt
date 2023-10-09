package com.catnip.rizkyilmann_challange4.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo(name = "position")
    var position: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "price")
    val price: Double,
    @ColumnInfo(name = "desc")
    val desc: String,
    @ColumnInfo(name = "product_img_url")
    val imgUrl: String
)