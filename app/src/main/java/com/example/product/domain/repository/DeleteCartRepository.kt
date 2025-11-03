package com.example.product.domain.repository

interface DeleteCartRepository {
    suspend fun deleteCart(productId: Int)
}