package com.thechance.caffeine.composable

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thechance.caffeine.R

@Composable
fun AnimatedHocusPocusText() {
    val infiniteTransition = rememberInfiniteTransition(label = "star_animation")

    val alpha by infiniteTransition.animateFloat(
        initialValue = 0.0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "star_alpha"
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Hocus",
                fontSize = 32.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = FontFamily(
                    Font(R.font.sniglet, FontWeight.Normal)
                ),
                color = Color(0xDE1F1F1F)
            )
            Spacer(modifier = Modifier.width(16.dp))
            AnimatedStar(alpha = alpha)
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            AnimatedStar(alpha = alpha)
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "Pocus",
                fontSize = 32.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = FontFamily(
                    Font(R.font.sniglet, FontWeight.Normal)
                ),
                color = Color(0xDE1F1F1F)
            )
        }

        Text(
            text = "I Need Coffee",
            fontSize = 32.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = FontFamily(
                Font(R.font.sniglet, FontWeight.Normal)
            ),
            color = Color(0xDE1F1F1F)
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "to Focus",
                fontSize = 32.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = FontFamily(
                    Font(R.font.sniglet, FontWeight.Normal)
                ),
                color = Color(0xDE1F1F1F)
            )
            Spacer(modifier = Modifier.width(16.dp))
            AnimatedStar(alpha = alpha)
        }
    }
}

@Composable
private fun AnimatedStar(alpha: Float) {
    Icon(
        modifier = Modifier.alpha(alpha),
        painter = painterResource(R.drawable.animated_star),
        contentDescription = null,
        tint = Color.Unspecified
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewAnimatedHocusPocusText() {
    AnimatedHocusPocusText()
}