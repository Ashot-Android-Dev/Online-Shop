package com.example.product.presentation.showUi.screens

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextMotion
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation3.runtime.NavKey
import com.example.product.R
import com.example.product.presentation.components.CustomButton
import com.example.product.presentation.components.CustomImage
import com.example.product.presentation.navigation.route.HomeDataScreen
import com.example.product.ui.theme.BackgroundColor
import com.example.product.ui.theme.PrimaryColor
import com.example.product.ui.theme.PrimaryVariantColor
import com.example.product.ui.theme.TitleTextStyle

@Composable
fun WelcomeScreen(
    modifier: Modifier = Modifier,
    onNavigate: (NavKey) -> Unit = {}
) {
    val infiniteTransition = rememberInfiniteTransition(label = "infiniteTransition")
    val scale  by infiniteTransition.animateFloat(
        initialValue = 0.5f,
        targetValue = 1.4f,
        animationSpec = infiniteRepeatable(tween (1500) , RepeatMode.Reverse),
        label = "scale"
    )
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundColor)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f)
        ) {
            CustomImage(
                image = R.drawable.shopingimage,
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.navigationBars)
                .clip(shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            PrimaryColor,
                            PrimaryVariantColor
                        )
                    )
                )
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = stringResource(R.string.title),
                modifier= Modifier
                    .graphicsLayer{
                        scaleX = scale
                        scaleY = scale
                        transformOrigin= TransformOrigin.Center
                    },
                style = TitleTextStyle.copy(
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    textMotion = TextMotion.Animated
                ),
            )
            Spacer(modifier = Modifier.weight(1f))
            CustomButton(
                str = R.string.start,
                fontSize = 25.sp,
                containerColor = Color.White,
                contentColor = PrimaryVariantColor
            ) {
                onNavigate(HomeDataScreen)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun WelcomeScreenPreview() {
    WelcomeScreen()
}
