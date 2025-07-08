package com.thechance.caffeine.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thechance.caffeine.R

@Composable
fun ButtonWithText(
    modifier: Modifier = Modifier,
    text: String,
    icon: Painter,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .background(
                color = Color(0xFF1F1F1F),
                shape = RoundedCornerShape(100.dp)
            )
            .padding(vertical = 16.dp, horizontal = 32.dp)
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xDEFFFFFF),
            fontFamily = FontFamily(
                Font(R.font.urbanist_bold, FontWeight.Bold)
            ),
        )
        Icon(
            modifier = Modifier.padding(start = 8.dp),
            painter = icon,
            contentDescription = null,
            tint = Color.Unspecified
        )
    }
}