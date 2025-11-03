package com.example.product.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FavoriteEntity::class, CartEntity::class], version = 1, exportSchema = false)
abstract class ProductDataBase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
    abstract fun cartDao(): CartDao

}