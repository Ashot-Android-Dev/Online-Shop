package com.example.product.domain.repository

interface DeleteProductRepo {
    suspend fun deleteProduct(productId: Int)
}