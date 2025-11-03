package com.example.product.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

enum class Destinations(
    val label: String,
    val contentDescription: String,
    val icon: ImageVector,
){
        HOME("Home","Home screen", Icons.Filled.Home),
        FAVORITE("Favorite","Favorite screen", Icons.Filled.Favorite),
        CART("Cart","Cart screen", Icons.Filled.ShoppingCart)


}