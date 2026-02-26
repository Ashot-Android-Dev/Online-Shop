package com.example.product.presentation.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.product.data.local.CartEntity
import com.example.product.ui.theme.GrayForBackround
import com.example.product.ui.theme.Yellow100
import com.example.product.viewModel.CartViewModel

@Composable
fun CartItemCard(
    modifier: Modifier = Modifier,
    cartEntity: CartEntity,
    cartViewModel: CartViewModel = viewModel(),
    isScrollInProgress: Boolean = false
) {
    var selectedQuantity by rememberSaveable { mutableIntStateOf(1) }
    var openDialog by remember { mutableStateOf(false) }
    var isExpanded by remember { mutableStateOf(false) }
    val totalPrice = cartEntity.price * selectedQuantity

    LaunchedEffect(isScrollInProgress) {
        if (isScrollInProgress) {
            isExpanded = false
        }
    }

    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioLowBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
            .clickable { isExpanded = !isExpanded },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.elevatedCardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Yellow100)
    ) {
        Column {
            Row {
                Box(
                    modifier = modifier
                ) {
                    AsyncImage(
                        model = cartEntity.thumbnail,
                        contentDescription = cartEntity.title,
                        modifier = Modifier
                            .height(150.dp)
                            .border(width = 1.dp, color = GrayForBackround, shape = RoundedCornerShape(12)),

                        )
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .clip(
                                RoundedCornerShape(
                                    topEnd = 8.dp,
                                    bottomEnd = 30.dp
                                )
                            )
                            .background(
                                Color.LightGray.copy(alpha = 0.4f), RoundedCornerShape(
                                    topEnd = 8.dp,
                                    bottomEnd = 30.dp
                                )
                            )
                            .padding(3.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = " -${cartEntity.discountPercentage}%",
                            fontWeight = FontWeight.Bold,
                            color = Color.Red,
                            fontSize = 16.sp,
                        )
                    }
                }
                Spacer(modifier = Modifier.width(8.dp))
                Column(modifier = Modifier.padding(6.dp)) {
                    Text(
                        text = " ${totalPrice}$",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Red
                    )
                    Text(
                        text = cartEntity.title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )
                    Text(
                        text = "Quantity: ${cartEntity.quantity}",
                        fontStyle = FontStyle.Italic,
                        color = Color.Gray
                    )

                }
            }
            if (isExpanded) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(onClick = { openDialog = true }) {
                        Icon(Icons.Filled.Delete, contentDescription = "null")
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Counter(
                        count = selectedQuantity,
                        onCountChange = { newQuantity ->
                            selectedQuantity = newQuantity.coerceIn(0, cartEntity.quantity)
                        },
                        min = 1,
                        max = cartEntity.quantity

                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    BuyButton { }
                }
            }
        }
    }
    if (openDialog) {
        DeleteAlertDialog(
            onDismissRequest = { openDialog = false },
            onConfirmation = {
                cartViewModel.removeCart(cartEntity.id)
                openDialog = false
            },
            dialogTitle = "Delete Product",
            dialogText = "Are you sure you want to delete this product?"

        )
    }
}
