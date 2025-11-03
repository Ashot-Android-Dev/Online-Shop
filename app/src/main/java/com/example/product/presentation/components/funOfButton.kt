package com.example.product.presentation.components

import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.product.ui.theme.GrayForBackround
import com.example.product.ui.theme.GreenForContent

@Composable
fun BuyButton(
    onClick: () -> Unit,
) {

    Button(
        onClick = { onClick() },
        modifier = Modifier.width(100.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = GrayForBackround,
            contentColor = GreenForContent
        )
    ) {
        Text(
            "Buy",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
