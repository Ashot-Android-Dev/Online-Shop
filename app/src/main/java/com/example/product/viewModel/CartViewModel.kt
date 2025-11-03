package com.example.product.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.product.domain.useCase.ProductUseCase
import com.example.product.domain.data.Cart
import com.example.product.domain.data.Product
import com.example.product.data.local.CartEntity
import com.example.product.data.local.FavoriteEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(private val productUseCase: ProductUseCase) : ViewModel() {
    private val _products = MutableStateFlow<List<Cart>>(emptyList())
    val products: StateFlow<List<Cart>> = _products.asStateFlow()

    private val _favorites = MutableStateFlow<List<FavoriteEntity>>(emptyList())
    val favorites: StateFlow<List<FavoriteEntity>> = _favorites.asStateFlow()

    private val _carts = MutableStateFlow<List<CartEntity>>(emptyList())
    val carts: StateFlow<List<CartEntity>> = _carts.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    val favoriteCount = favorites.map { it.size }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000), 0,
    )

    val cartCount = carts.map { it.size }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000), 0
    )

    init {
        loadProducts()
        loadFavorites()
        loadCarts()
    }

    fun loadProducts() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val result = productUseCase.getCartsUseCase()
                _products.value = result
            } catch (e: Exception) {
                Log.e("CartViewModel", "Error loading products", e)
            } finally {
                _isLoading.value = false

            }

        }
    }

    fun loadFavorites() {
        viewModelScope.launch {
            try {
                _favorites.value = productUseCase.getAllFavoritesUseCase()
            } catch (e: Exception) {
                Log.e("CartViewModel", "Error loading favorites", e)
            }
        }
    }

    fun loadCarts(){
        viewModelScope.launch {
            try {
                _carts.value=productUseCase.getAllAddCartsUseCase()
            }catch (e: Exception){
                Log.e("CartViewModel", "Error loading carts", e)
            }
        }
    }

    fun addFavorite(product: Product) {
        viewModelScope.launch {
            try {
                if (_favorites.value.any { it -> it.id == product.id })
                    return@launch

                val favorite = FavoriteEntity(
                    id = product.id,
                    title = product.title,
                    price = product.price,
                    quantity = product.quantity,
                    total = product.total,
                    discountPercentage = product.discountPercentage,
                    discountedTotal = product.discountedTotal,
                    thumbnail = product.thumbnail,
                )
                productUseCase.addFavoriteUseCase(favorite)
                _favorites.value += favorite
            } catch (e: Exception) {
                Log.e("CartViewModel", "Error loading products", e)
            }
        }
    }

    fun addToCart(product: Product) {
        viewModelScope.launch {
            try {
                if (_carts.value.any { it -> it.id == product.id }) return@launch
                val cart = CartEntity(
                    id = product.id,
                    title = product.title,
                    price = product.price,
                    quantity = product.quantity,
                    total = product.total,
                    discountPercentage = product.discountPercentage,
                    discountedTotal = product.discountedTotal,
                    thumbnail = product.thumbnail,
                )
                productUseCase.addToCartUseCase(cart)
                _carts.value = _carts.value + cart
            } catch (e: Exception) {
                Log.e(
                    "CartViewModel", "Error loading products", e
                )
            }
        }
    }


    fun removeFavorite(productId: Int) {
        viewModelScope.launch {
            try {
                productUseCase.deleteFavoriteUseCase(productId)
                _favorites.value = _favorites.value.filterNot { it.id == productId }
            } catch (e: Exception) {
                Log.e("CartViewModel", "Error removing favorite", e)
            }
        }
    }

    fun removeCart(productId: Int) {
        viewModelScope.launch {
            try {
                productUseCase.deleteCartUseCase(productId)
                _carts.value = _carts.value.filterNot { it.id == productId }
            } catch (e: Exception) {
                Log.e("CartViewModel", "Error removing cart", e)
            }
        }


    }
}