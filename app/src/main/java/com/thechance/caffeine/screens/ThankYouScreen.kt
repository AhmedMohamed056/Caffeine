package com.thechance.caffeine.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.thechance.caffeine.composable.ButtonWithText

@Composable
fun ThankYouScreen(
    snack: Snack
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(top = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier
                .padding(start = 16.dp)
                .background(
                    color = Color(0xFFF5F5F5),
                    shape = CircleShape
                )
                .padding(12.dp)
                .align(Alignment.Start),
            painter = painterResource(R.drawable.ic_close),
            contentDescription = null,
            tint = Color.Unspecified
        )
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        ) {
            Icon(
                modifier = Modifier.size(32.dp),
                painter = painterResource(R.drawable.coffee_beans1),
                contentDescription = null,
                tint = Color.Unspecified
            )

            Text(
                text = "More Espresso, Less Depresso",
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal,
                color = Color(0xFF7C351B),
                fontFamily = FontFamily(
                    Font(R.font.sniglet, FontWeight.Normal)
                ),
            )
            Icon(
                modifier = Modifier.size(32.dp),
                painter = painterResource(R.drawable.coffee_beans1),
                contentDescription = null,
                tint = Color.Unspecified
            )
        }
        Image(
            modifier = Modifier.size(300.dp),
            painter = painterResource(id = snack.snackImg),
            contentDescription = null,
        )
        Row(
            modifier = Modifier.padding(top = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Bon appétit",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(
                    Font(R.font.urbanist_bold, FontWeight.Bold)
                ),
                color = Color(0xCC1F1F1F)
            )
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(R.drawable.magic_wand),
                contentDescription = null,
                tint = Color.Unspecified
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        ButtonWithText(
            modifier = Modifier.padding(bottom = 50.dp),
            text = "thank you",
            icon = painterResource(R.drawable.arrow_right_coffe),
            onClick = {}
        )
    }
}