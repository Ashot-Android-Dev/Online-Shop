package com.example.product.domain.repository

import com.example.product.data.local.CartEntity

interface AddToCartRepository {
    suspend fun addToCart(cartEntity: CartEntity)
}