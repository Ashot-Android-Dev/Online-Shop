package com.example.product.presentation.showUi.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Accessibility
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.product.presentation.navigation.Destinations
import com.example.product.viewModel.CartViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: CartViewModel = viewModel(),
) {
    val favoriteCount by viewModel.favoriteCount.collectAsState()
    val cartCount by viewModel.cartCount.collectAsState()
    var currentDestination by rememberSaveable { mutableStateOf(Destinations.HOME) }

    val navigationItemColors = NavigationSuiteDefaults.itemColors(
        navigationBarItemColors = androidx.compose.material3.NavigationBarItemDefaults.colors(
            indicatorColor = MaterialTheme.colorScheme.primaryContainer,
            selectedIconColor = MaterialTheme.colorScheme.primary,
            selectedTextColor = MaterialTheme.colorScheme.primary,
            unselectedIconColor = Color.Gray,
            unselectedTextColor = Color.Gray,
        )
    )

    NavigationSuiteScaffold(
        layoutType = androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType.NavigationBar,
        navigationSuiteItems = {
            Destinations.entries.forEach { destination ->
                val badgeCount = when (destination) {
                    Destinations.FAVORITE -> favoriteCount
                    Destinations.CART -> cartCount
                    else -> 0
                }
                item(
                    icon = {
                        BadgedBox(
                            badge = {
                                if (badgeCount > 0) {
                                    Badge { Text(badgeCount.toString()) }
                                }
                            }
                        ) {
                            Icon(destination.icon, contentDescription = destination.contentDescription)
                        }
                    },
                    label = { Text(destination.label) },
                    selected = destination == currentDestination,
                    onClick = { currentDestination = destination },
                    colors = navigationItemColors
                )
            }
        },
        content = {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(currentDestination.label) },
                        actions = {
                            IconButton(onClick = {}) {
                                Icon(Icons.Default.Accessibility, contentDescription = null)
                            }
                        }
                    )
                }
            ) { paddingValues ->
                Box(modifier = Modifier.padding(paddingValues)) {
                    when (currentDestination) {
                        Destinations.HOME -> HomeScreen(
                            imageVector = currentDestination.icon,
                            contentDescription = currentDestination.contentDescription,
                            viewModel = viewModel
                        )
                        Destinations.FAVORITE -> FavoriteScreen(
                            modifier = Modifier,
                            icon = currentDestination.icon,
                            contentDescription = currentDestination.contentDescription,
                            cartViewModel = viewModel
                        )
                        Destinations.CART -> CartScreen(
                            modifier = Modifier,
                            icon = currentDestination.icon,
                            contentDescription = currentDestination.contentDescription,
                            cartViewModel = viewModel
                        )
                    }
                }
            }
        }
    )
}
