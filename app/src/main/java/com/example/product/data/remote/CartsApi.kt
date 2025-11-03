package com.example.product.data.remote

import com.example.product.data.remote.dto.CartResponse
import retrofit2.http.GET

interface CartsApi {
    @GET("carts")
    suspend fun getCarts(): CartResponse
}