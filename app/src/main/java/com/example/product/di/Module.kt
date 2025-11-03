package com.example.product.di

import android.content.Context
import androidx.room.Room
import com.example.product.data.repoImpl.AddFavoriteProductImpl
import com.example.product.domain.repository.AddFavoriteProductRepository
import com.example.product.domain.repository.GettAllFavoriteRepository
import com.example.product.data.repoImpl.GettAllFavoriteRepositoryImpl
import com.example.product.domain.repository.AddToCartRepository
import com.example.product.data.repoImpl.AddToCartRepositoryImpl
import com.example.product.data.repoImpl.CartRepositoryImpl
import com.example.product.domain.repository.DeleteCartRepository
import com.example.product.data.repoImpl.DeleteCartRepositoryImpl
import com.example.product.domain.repository.DeleteProductRepo
import com.example.product.data.repoImpl.DeleteProductRepoImpl
import com.example.product.domain.repository.GetAllAddCartsRepo
import com.example.product.data.repoImpl.GetAllAddCartsRepoImpl
import com.example.product.domain.repository.GetCartsRepository
import com.example.product.data.local.ProductDataBase
import com.example.product.data.remote.CartsApi
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://dummyjson.com/"

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideInstanceRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideCartsApi(retrofit: Retrofit): CartsApi {
        return retrofit.create(CartsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context): ProductDataBase {
        return Room.databaseBuilder(
            context = context,
            klass = ProductDataBase::class.java,
            name = "product_database"
        ).build()
    }


    @Provides
    @Singleton
    fun provideFavoriteDao(productDataBase: ProductDataBase) = productDataBase.favoriteDao()

    @Provides
    @Singleton
    fun provideCartDao(productDataBase: ProductDataBase) = productDataBase.cartDao()
}


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindCartsRepository(impl: CartRepositoryImpl): GetCartsRepository

    @Binds
    @Singleton
    abstract fun bindGetAllFavoriteRepository(impl: GettAllFavoriteRepositoryImpl): GettAllFavoriteRepository

    @Binds
    @Singleton
    abstract fun bindAddToCartRepository(impl: AddToCartRepositoryImpl): AddToCartRepository

    @Binds
    @Singleton
    abstract fun bindAddFavoriteRepository(impl: AddFavoriteProductImpl): AddFavoriteProductRepository

    @Binds
    @Singleton
    abstract fun bindDeleteProduct(impl: DeleteProductRepoImpl): DeleteProductRepo

    @Binds
    @Singleton
    abstract fun bindDeleteCartRepository(impl: DeleteCartRepositoryImpl): DeleteCartRepository

    @Binds
    @Singleton
    abstract fun bindGetAllAddCartsRepository(impl: GetAllAddCartsRepoImpl): GetAllAddCartsRepo

}
