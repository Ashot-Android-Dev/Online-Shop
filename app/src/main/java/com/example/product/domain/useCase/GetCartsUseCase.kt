package com.example.product.domain.useCase

import com.example.product.domain.repository.AddFavoriteProductRepository
import com.example.product.domain.repository.GettAllFavoriteRepository
import com.example.product.domain.repository.AddToCartRepository
import com.example.product.domain.repository.DeleteCartRepository
import com.example.product.domain.repository.DeleteProductRepo
import com.example.product.domain.repository.GetAllAddCartsRepo
import com.example.product.domain.repository.GetCartsRepository
import com.example.product.data.remote.dto.CartResponse
import com.example.product.data.local.CartEntity
import com.example.product.data.local.FavoriteEntity
import com.example.product.domain.data.Cart
import javax.inject.Inject

class GetCartsUseCase @Inject constructor(private val getCartsRepository: GetCartsRepository) {
    suspend operator fun invoke(): List<Cart> {
        return getCartsRepository.getAllCarts()
    }
}

class GetAllFavoriteUseCase @Inject constructor(private val gettAllFavoriteRepository: GettAllFavoriteRepository) {
    suspend operator fun invoke(): List<FavoriteEntity> {
        return gettAllFavoriteRepository.gettAllFavorite()
    }
}

class GetAllAddCartsUseCase @Inject constructor(private val getAllAddCartsRepo: GetAllAddCartsRepo){
    suspend operator fun invoke(): List<CartEntity> {
        return getAllAddCartsRepo.getAllAddCarts()
    }
}
class AddToCartUseCase @Inject constructor(private val addToCartRepository: AddToCartRepository) {
    suspend operator fun invoke(cartEntity: CartEntity) {
        return addToCartRepository.addToCart(cartEntity)
    }

}

class AddFavoriteUseCase @Inject constructor(private val addFavoriteProductRepository: AddFavoriteProductRepository) {
    suspend operator fun invoke(favoriteEntity: FavoriteEntity) {
        return addFavoriteProductRepository.addFavoriteProduct(favoriteEntity)
    }
}

class DeleteFavoriteUseCase @Inject constructor(private val deleteProductRepo: DeleteProductRepo) {
    suspend operator fun invoke(productId: Int) {
        return deleteProductRepo.deleteProduct(productId)
    }
}

class DeleteCartUseCase @Inject constructor(private val deleteCartRepository: DeleteCartRepository) {
    suspend operator fun invoke(productId: Int) {
        return deleteCartRepository.deleteCart(productId)
    }
}



data class ProductUseCase @Inject constructor(
    val getCartsUseCase: GetCartsUseCase,
    val getAllFavoritesUseCase: GetAllFavoriteUseCase,
    val addToCartUseCase: AddToCartUseCase,
    val addFavoriteUseCase: AddFavoriteUseCase,
    val deleteFavoriteUseCase: DeleteFavoriteUseCase,
    val deleteCartUseCase: DeleteCartUseCase,
    val getAllAddCartsUseCase: GetAllAddCartsUseCase,
)