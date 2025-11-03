package com.example.product.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.product.ui.theme.GrayForBackround
import com.example.product.ui.theme.GreenForContent

@Composable
fun Counter(
    modifier: Modifier = Modifier,
    count: Int,
    onCountChange: (Int) -> Unit,
    min: Int = 1,
    max: Int = 99
) {
    Card (shape = RoundedCornerShape(14.dp)){
        Row(
            modifier = modifier
                .width(100.dp)
                .background(color = GrayForBackround)
                .padding(4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            IconButton(
                onClick = { if (count > min) onCountChange(count - 1) },
                modifier = Modifier.size(32.dp)
            ) {
                Icon(
                    Icons.Default.Remove, contentDescription = "Decrease",
                    tint = Color.Gray
                )
            }

            Text(
                text = count.toString(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 20.sp
            )

            IconButton(
                onClick = { if (count < max) onCountChange(count + 1) },
                modifier = Modifier.size(32.dp)
            ) {
                Icon(
                    Icons.Default.Add, contentDescription = "Increase",
                    tint = GreenForContent
                )
            }
        }
    }
}