package com.example.product.presentation.showUi.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.product.presentation.components.FavoriteComponent
import com.example.product.viewModel.CartViewModel

@SuppressLint("SuspiciousIndentation")
@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    contentDescription: String,
    cartViewModel: CartViewModel = viewModel()
) {
    val favorites by cartViewModel.favorites.collectAsState()


    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.systemBars)
    ) {
        items(favorites) { favorites ->
            FavoriteComponent(favoriteEntity = favorites)
        }
    }

}



