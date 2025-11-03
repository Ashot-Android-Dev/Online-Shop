package com.example.product.data.repoImpl

import com.example.product.data.mapper.toDomain
import com.example.product.domain.repository.GetCartsRepository
import com.example.product.data.remote.CartsApi
import com.example.product.domain.data.Cart
import jakarta.inject.Inject

class CartRepositoryImpl @Inject constructor(private val cartsApi: CartsApi) : GetCartsRepository {
    override suspend fun getAllCarts(): List<Cart> {
        val responce = cartsApi.getCarts()
        return responce.carts.map { it.toDomain() }
    }

}