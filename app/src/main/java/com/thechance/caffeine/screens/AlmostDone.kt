package com.thechance.caffeine.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
fun AlmostDone(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        WavyLoadingLine()
        Spacer(Modifier.height(16.dp))
        Text(
            text = "Almost Done",
            fontSize = 22.sp,
            color = Color(0xDE1F1F1F),
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily(
                Font(R.font.urbanist_bold, FontWeight.Bold)
            )
        )
        Text(
            text = "Your coffee will be finish in",
            fontSize = 16.sp,
            color = Color(0x991F1F1F),
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily(
                Font(R.font.urbanist_bold, FontWeight.Bold)
            )
        )
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                painter = painterResource(R.drawable.co),
                contentDescription = null,
                tint = Color.Unspecified
            )
            Text(
                modifier = Modifier.padding(horizontal = 12.dp),
                text = ":",
                fontSize = 24.sp,
                color = Color(0x1F1F1F1F),
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(
                    Font(R.font.urbanist_bold, FontWeight.Bold)
                )
            )
            Icon(
                painter = painterResource(R.drawable.ff),
                contentDescription = null,
                tint = Color.Unspecified
            )
            Text(
                modifier = Modifier.padding(horizontal = 12.dp),
                text = ":",
                fontSize = 24.sp,
                color = Color(0x1F1F1F1F),
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(
                    Font(R.font.urbanist_bold, FontWeight.Bold)
                )
            )
            Icon(
                painter = painterResource(R.drawable.ee),
                contentDescription = null,
                tint = Color.Unspecified
            )
        }
    }
}