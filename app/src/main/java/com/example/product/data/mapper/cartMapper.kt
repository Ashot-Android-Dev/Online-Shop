package com.example.product.data.mapper

import com.example.product.data.remote.dto.CartDto
import com.example.product.data.remote.dto.ProductDto
import com.example.product.domain.data.Cart
import com.example.product.domain.data.Product

fun ProductDto.toDomain(): Product {
    return Product(
        id = id,
        title = title,
        price = price,
        quantity = quantity,
        total = total,
        discountPercentage = discountPercentage,
        discountedTotal = discountedTotal,
        thumbnail = thumbnail,
    )
}

fun CartDto.toDomain(): Cart {
    return Cart(
        id = id,
        products = products.map { it.toDomain() },
        total = total,
        discountedTotal = discountedTotal,
        userId = userId,
        totalProducts = totalProducts,
        totalQuantity = totalQuantity,

    )
}