package com.thechance.caffeine.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.thechance.caffeine.R

@Composable
fun AppBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .height(48.dp)
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            modifier = Modifier.size(48.dp),
            painter = painterResource(R.drawable.appbar_img),
            contentDescription = "header img"
        )

        Icon(
            modifier = Modifier
                .background(
                    color = Color(0xFFF5F5F5),
                    shape = CircleShape
                )
                .padding(12.dp),
            painter = painterResource(R.drawable.add_coffee),
            contentDescription = null,
            tint = Color.Unspecified
        )
    }
}