package com.example.product.data.repoImpl

import com.example.product.domain.repository.DeleteCartRepository
import com.example.product.data.local.CartDao
import jakarta.inject.Inject

class DeleteCartRepositoryImpl @Inject constructor(private val cartDao: CartDao) :
    DeleteCartRepository {
    override suspend fun deleteCart(productId: Int) {
        return cartDao.deleteCartById(productId)
    }
}