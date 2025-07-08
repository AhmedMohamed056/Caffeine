package com.thechance.caffeine.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thechance.caffeine.R

@Composable
fun OrderingHeader(
    drink: CoffeeDrink,
    onClickBack: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 65.dp)
            .height(48.dp)
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .background(
                    color = Color(0xFFF5F5F5),
                    shape = CircleShape
                )
                .clickable(onClick = onClickBack)
                .padding(12.dp),
            painter = painterResource(R.drawable.arrow_right_co),
            contentDescription = null,
            tint = Color.Unspecified
        )
        Text(
            text = drink.name,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xDE1F1F1F),
            fontFamily = FontFamily(
                Font(R.font.urbanist_bold, FontWeight.Bold)
            ),
        )
    }
}