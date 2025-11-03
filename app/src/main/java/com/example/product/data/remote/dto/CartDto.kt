package com.example.product.data.remote.dto

import kotlinx.serialization.Serializable


data class CartResponse(
    val carts: List<CartDto>,
)
data class CartDto(
    val id: Int,
    val products: List<ProductDto>,
    val total: Double,
    val discountedTotal: Double,
    val userId: Int,
    val totalProducts: Int,
    val totalQuantity: Int,
)

@Serializable
data class ProductDto(
    val id: Int,
    val title: String,
    val price: Double,
    val quantity: Int,
    val total: Double,
    val discountPercentage: Double,
    val discountedTotal: Double,
    val thumbnail: String,
)