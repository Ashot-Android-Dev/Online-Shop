package com.example.product.domain.repository

import com.example.product.data.remote.dto.CartResponse
import com.example.product.domain.data.Cart

interface GetCartsRepository {
    suspend fun getAllCarts(): List<Cart>
}