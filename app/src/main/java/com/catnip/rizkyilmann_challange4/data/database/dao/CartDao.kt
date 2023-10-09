package com.catnip.rizkyilmann_challange4.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.catnip.rizkyilmann_challange4.data.database.entity.CartEntity
import com.catnip.rizkyilmann_challange4.data.database.relation.CartProductRelation
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {

    @Query("SELECT * FROM CARTS")
    fun getAllCarts(): Flow<List<CartProductRelation>>

    @Query("SELECT * FROM CARTS WHERE id == :cartId")
    fun getCartById(cartId: Int): Flow<CartProductRelation>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCart(cart: CartEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCarts(product: List<CartEntity>)

    @Delete
    suspend fun deleteCart(cart: CartEntity): Int

    @Update
    suspend fun updateCart(cart: CartEntity): Int
}