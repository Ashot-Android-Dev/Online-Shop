package com.example.product.data.repoImpl

import com.example.product.domain.repository.GetAllAddCartsRepo
import com.example.product.data.local.CartDao
import com.example.product.data.local.CartEntity
import javax.inject.Inject

class GetAllAddCartsRepoImpl @Inject constructor(private val cartDao: CartDao): GetAllAddCartsRepo {
    override suspend fun getAllAddCarts(): List<CartEntity> {
        return cartDao.getAllAddCarts()
    }
}