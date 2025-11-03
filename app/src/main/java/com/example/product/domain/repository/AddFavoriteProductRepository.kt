package com.example.product.domain.repository

import com.example.product.data.local.FavoriteEntity

interface AddFavoriteProductRepository {
    suspend fun addFavoriteProduct(favoriteEntity: FavoriteEntity)
}