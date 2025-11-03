package com.example.product.presentation.showUi.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
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
    val textFieldState = remember { TextFieldState() }
    val searchResults = remember { mutableListOf("Shoes", "bags", "T -shirts") }
    var destinations by rememberSaveable { mutableStateOf(Destinations.HOME) }


    val navigationItemColors = NavigationSuiteDefaults.itemColors(
        navigationRailItemColors = NavigationRailItemDefaults.colors(
            indicatorColor = Color.Magenta,
            selectedIconColor = MaterialTheme.colorScheme.onPrimary,
            selectedTextColor = MaterialTheme.colorScheme.primary,
            unselectedIconColor = Color.DarkGray,
            unselectedTextColor = Color.DarkGray,
        )
    )
    NavigationSuiteScaffold(
        navigationSuiteItems = {
            Destinations.entries.forEach {
                val badgeCount = when (it) {
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
                            Icon(it.icon, contentDescription = it.contentDescription)
                        }
                    },
                    label = { Text(it.label) },
                    selected = it == destinations,
                    onClick = { destinations = it },
                    colors = navigationItemColors
                )
            }
        },
        content = {
            Spacer(modifier = Modifier.height(16.dp))
            when (destinations) {
                Destinations.HOME -> HomeScreen(
                    modifier,
                    destinations.icon,
                    destinations.contentDescription,
                )

                Destinations.FAVORITE -> FavoriteScreen(
                    modifier,
                    destinations.icon,
                    destinations.contentDescription
                )

                Destinations.CART -> CartScreen(
                    modifier,
                    destinations.icon,
                    destinations.contentDescription
                )
            }

        }
    )
}

