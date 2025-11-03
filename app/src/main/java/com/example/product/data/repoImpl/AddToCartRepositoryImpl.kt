package com.example.product.data.repoImpl

import com.example.product.domain.repository.AddToCartRepository
import com.example.product.data.local.CartDao
import com.example.product.data.local.CartEntity
import jakarta.inject.Inject

class AddToCartRepositoryImpl @Inject constructor(private val cartDao: CartDao):
    AddToCartRepository {
    override suspend fun addToCart(cartEntity: CartEntity) {
        return cartDao.insertCart(cartEntity)
    }

}