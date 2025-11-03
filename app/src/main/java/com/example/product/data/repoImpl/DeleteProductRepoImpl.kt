package com.example.product.data.repoImpl

import com.example.product.domain.repository.DeleteProductRepo
import com.example.product.data.local.FavoriteDao
import javax.inject.Inject

class DeleteProductRepoImpl @Inject constructor(private val favoriteDao: FavoriteDao):
    DeleteProductRepo {
    override suspend fun deleteProduct(productId: Int) {
        return favoriteDao.deleteFavoriteById(productId)
    }
}