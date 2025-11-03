package com.example.product.domain.data

data class CartResponse(
    val carts: List<Cart>,
)
data class Cart(
    val id: Int,
    val products: List<Product>,
    val total: Double,
    val discountedTotal: Double,
    val userId: Int,
    val totalProducts: Int,
    val totalQuantity: Int,
)

data class Product(
    val id: Int,
    val title: String,
    val price: Double,
    val quantity: Int,
    val total: Double,
    val discountPercentage: Double,
    val discountedTotal: Double,
    val thumbnail: String,
)