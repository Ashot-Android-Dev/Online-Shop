package com.example.product.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.product.ui.theme.DarkYellow
import com.example.product.ui.theme.PrimaryText

@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    containerColor: Color = DarkYellow,
    contentColor: Color = PrimaryText,
    fontSize: TextUnit = 18.sp,
    str: Int,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .padding(horizontal = 20.dp)
            .height(56.dp)
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        )
    ) {
        Text(
            text = stringResource(str),
            fontSize = fontSize
        )
    }
}
