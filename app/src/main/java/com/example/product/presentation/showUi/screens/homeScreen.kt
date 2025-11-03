package com.example.product.presentation.showUi.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.product.domain.data.Product
import com.example.product.ui.theme.DarkYellow
import com.example.product.ui.theme.Yellow100


import com.example.product.viewModel.CartViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    contentDescription: String,
    viewModel: CartViewModel = viewModel(),
    onNavigate: (Product) -> Unit = {}
    ) {
    val lazyVerticalGridState = rememberLazyGridState()
    val context = LocalContext.current
    val products by viewModel.products.collectAsState()
    val favorite by viewModel.favorites.collectAsState()
    val cart by viewModel.carts.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    Box(modifier = Modifier.fillMaxSize(),) {
        when {
            isLoading ->
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = Color.Gray
                )

            products.isEmpty() -> {
                Text(text = "No products found",
                    modifier = Modifier.align(Alignment.Center),
                    fontSize = 20.sp)
            }

            else -> {
                LazyVerticalGrid(
                    modifier = Modifier.windowInsetsPadding(WindowInsets.systemBars),
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(4.dp),
                    verticalArrangement = Arrangement.spacedBy(5.dp),
                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                    state = lazyVerticalGridState
                ) {
                    items(products.flatMap { it.products }) { product ->
                        val isFavorite = favorite.any { it.id == product.id }
                        val isCart = cart.any { it.id == product.id }
                        ProductItem(
                            product = product,
                            isFavorite = isFavorite,
                            isCart = isCart,
                            onClickFavorite = {
                                if (isFavorite) viewModel.removeFavorite(product.id) else viewModel.addFavorite(
                                    product = product
                                )
                                Toast.makeText(context, product.title, Toast.LENGTH_SHORT).show()
                            },
                            onClickCart = {
                                if (viewModel.carts.value.any { it -> it.id == product.id }) {
                                    viewModel.removeCart(product.id)
                                } else viewModel.addToCart(product)
                                Toast.makeText(context, product.title, Toast.LENGTH_SHORT).show()
                            },
                            onClick = {onNavigate(product)}
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ProductItem(
    modifier: Modifier = Modifier,
    product: Product,
    onClickFavorite: () -> Unit,
    onClickCart: () -> Unit = {},
    isFavorite: Boolean,
    isCart: Boolean,
    onClick: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .width(187.dp)
            .border(width = 1.dp, color = Color.LightGray, shape = RoundedCornerShape(10.dp))
            .clip(shape = RoundedCornerShape(12.dp))
            .background(color = Yellow100)
            .padding(4.dp)
            .clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = modifier
        ) {
            AsyncImage(
                model = product.thumbnail,
                contentDescription = product.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                ,
                contentScale = ContentScale.Crop
            )
            IconButton(
                onClick = onClickFavorite,
                modifier = Modifier
                    .align(Alignment.TopEnd)
            ) {
                Icon(
                    if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                    contentDescription = null,
                    tint = if (isFavorite) MaterialTheme.colorScheme.secondary else Color.Gray


                )

            }
            Box(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .clip(
                        RoundedCornerShape(
//                            topStart = 30.dp,
                            topEnd = 12.dp,
//                            bottomStart = 8.dp,
                            bottomEnd = 12.dp
                        )
                    )
                    .background(
                        Color.LightGray.copy(alpha = 0.4f), RoundedCornerShape(
//                            topStart = 30.dp,
                            topEnd = 12.dp,
//                            bottomStart = 8.dp,
                            bottomEnd = 12.dp
                        )
                    )
                    .padding(4.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = " -${product.discountPercentage}%",
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = Color.Red,
                    fontSize = 16.sp,

                    )
            }
        }
        HorizontalDivider(
            modifier = Modifier.fillMaxSize(),
            color = Color.LightGray
        )
        Row(modifier = Modifier.fillMaxWidth()) {
            Icon(
                Icons.Filled.Star, contentDescription = "Star",
                tint = DarkYellow
            )
            Text(text = "4.8")
            Text(
                text = ".8515 grades",
                fontSize = 15.sp,
                color = Color.Gray
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(3.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${product.price}$",
                fontSize = 20.sp,
                color = Color(0xFFE91E2F),
                fontWeight = FontWeight.Bold,
            )

            Spacer(Modifier.weight(1f))
            Box(
                modifier = Modifier
                    .size(35.dp)
                    .background(
                        color = if (isCart) Color.LightGray else MaterialTheme.colorScheme.secondary, RoundedCornerShape(10.dp)
                    ),
            ) {
                IconButton(onClick = onClickCart) {
                    Icon(
                        if (isCart) Icons.Filled.Check else Icons.Filled.AddShoppingCart,
                        contentDescription = null,
                        tint = if (isCart) MaterialTheme.colorScheme.secondary else Color.LightGray
                    )
                }
            }
        }
    }
}
