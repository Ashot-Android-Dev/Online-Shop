package com.example.product.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favoriteEntity: FavoriteEntity)

    @Query("SELECT * FROM favorite_table")
    suspend fun getAllFavorites(): List<FavoriteEntity>

    @Delete
    suspend fun deleteFavorite(favoriteEntity: FavoriteEntity)

    @Query("DELETE FROM favorite_table WHERE id = :id")
    suspend fun deleteFavoriteById(id: Int)

}
@Dao
interface CartDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCart(cartEntity: CartEntity)

    @Query("SELECT * FROM cart_table")
    suspend fun getAllAddCarts(): List<CartEntity>

    @Query("DELETE FROM cart_table WHERE id = :id")
    suspend fun deleteCartById(id: Int)

    @Delete
    suspend fun deleteCart(cartEntity: CartEntity)

}