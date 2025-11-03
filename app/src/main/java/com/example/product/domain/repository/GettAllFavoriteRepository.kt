package com.example.product.domain.repository

import com.example.product.data.local.FavoriteEntity

interface GettAllFavoriteRepository {
    suspend fun gettAllFavorite():List<FavoriteEntity>
}