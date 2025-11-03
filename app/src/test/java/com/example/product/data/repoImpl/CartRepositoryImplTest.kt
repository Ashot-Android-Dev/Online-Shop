package com.example.product.data.repoImpl

import com.example.product.data.remote.CartsApi
import com.example.product.data.remote.dto.CartDto
import com.example.product.data.remote.dto.CartResponse
import com.example.product.data.remote.dto.ProductDto
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

class CartRepositoryImplTest {

    @Mock
    private lateinit var cartsApi: CartsApi

    private lateinit var cartRepository: CartRepositoryImpl

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        cartRepository = CartRepositoryImpl(cartsApi)
    }

    @Test
    fun `getAllCarts should return list of carts from API`() = runTest {
        // Given
        val productDto = ProductDto(
            id = 1,
            title = "Product 1",
            price = 10.0,
            quantity = 1,
            total = 10.0,
            discountPercentage = 0.0,
            discountedTotal = 10.0,
            thumbnail = "thumb"
        )
        val cartDto = CartDto(
            id = 1,
            products = listOf(productDto),
            total = 10.0,
            discountedTotal = 10.0,
            userId = 1,
            totalProducts = 1,
            totalQuantity = 1
        )
        val cartResponse = CartResponse(carts = listOf(cartDto))

        whenever(cartsApi.getCarts()).thenReturn(cartResponse)

        // When
        val result = cartRepository.getAllCarts()

        // Then
        assertEquals(1, result.size)
        assertEquals(1, result[0].id)
        assertEquals("Product 1", result[0].products[0].title)
    }
}