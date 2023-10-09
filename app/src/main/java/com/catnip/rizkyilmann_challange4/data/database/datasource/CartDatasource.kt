package com.catnip.rizkyilmann_challange4.data.database.datasource

import com.catnip.rizkyilmann_challange4.data.database.dao.CartDao
import com.catnip.rizkyilmann_challange4.data.database.entity.CartEntity
import com.catnip.rizkyilmann_challange4.data.database.relation.CartProductRelation
import kotlinx.coroutines.flow.Flow

interface CartDataSource {
    fun getAllCarts(): Flow<List<CartProductRelation>>
    fun getCartById(cartId: Int): Flow<CartProductRelation>
    suspend fun insertCart(cart: CartEntity) : Long
    suspend fun deleteCart(cart: CartEntity): Int
    suspend fun updateCart(cart: CartEntity): Int
}

class CartDatabaseDataSource(private val cartDao: CartDao) : CartDataSource {
    override fun getAllCarts(): Flow<List<CartProductRelation>> {
        return cartDao.getAllCarts()
    }

    override fun getCartById(cartId: Int): Flow<CartProductRelation> {
        return cartDao.getCartById(cartId)
    }

    override suspend fun insertCart(cart: CartEntity): Long {
        return cartDao.insertCart(cart)
    }

    override suspend fun deleteCart(cart: CartEntity): Int {
        return cartDao.deleteCart(cart)
    }

    override suspend fun updateCart(cart: CartEntity): Int {
        return cartDao.updateCart(cart)
    }

}