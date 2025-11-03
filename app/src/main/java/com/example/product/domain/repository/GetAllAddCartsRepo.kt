package com.example.product.domain.repository

import com.example.product.data.local.CartEntity

interface GetAllAddCartsRepo {
    suspend fun getAllAddCarts(): List<CartEntity>

}