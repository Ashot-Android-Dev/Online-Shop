package com.example.product.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.product.presentation.navigation.route.HomeDataScreen
import com.example.product.presentation.navigation.route.Welcome
import com.example.product.presentation.showUi.screens.MainScreen
import com.example.product.presentation.showUi.screens.WelcomeScreen


@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val backStack = rememberNavBackStack(Welcome)

    val onNavigate: (NavKey) -> Unit = {
        backStack.add(it)
    }
    NavDisplay(
        modifier = modifier,
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<Welcome> {
                WelcomeScreen(onNavigate = onNavigate)
            }
            entry<HomeDataScreen> {
                MainScreen()
            }
        }
    )
}
