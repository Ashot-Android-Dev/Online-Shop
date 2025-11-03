package com.example.product.data.repoImpl

import com.example.product.domain.repository.GettAllFavoriteRepository
import com.example.product.data.local.FavoriteDao
import com.example.product.data.local.FavoriteEntity
import jakarta.inject.Inject

class GettAllFavoriteRepositoryImpl @Inject constructor(private val favoriteDao: FavoriteDao):
    GettAllFavoriteRepository {
    override suspend fun gettAllFavorite() : List<FavoriteEntity> {
        return favoriteDao.getAllFavorites()
    }
}