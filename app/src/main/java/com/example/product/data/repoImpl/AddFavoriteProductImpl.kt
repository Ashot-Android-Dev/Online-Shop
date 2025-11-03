package com.example.product.data.repoImpl

import com.example.product.domain.repository.AddFavoriteProductRepository
import com.example.product.data.local.FavoriteDao
import com.example.product.data.local.FavoriteEntity
import javax.inject.Inject

class AddFavoriteProductImpl @Inject constructor(private val favoriteDao: FavoriteDao):
    AddFavoriteProductRepository {
    override suspend fun addFavoriteProduct(favoriteEntity: FavoriteEntity) {
        return favoriteDao.insertFavorite(favoriteEntity)
    }
}