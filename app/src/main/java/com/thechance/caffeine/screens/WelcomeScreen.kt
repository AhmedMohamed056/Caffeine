package com.thechance.caffeine.screens

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.thechance.caffeine.R
import com.thechance.caffeine.composable.AnimatedHocusPocusText
import com.thechance.caffeine.composable.AppBar
import com.thechance.caffeine.composable.ButtonWithText
import kotlin.math.abs

@Composable
fun WelcomeScreen(
    onClick: () -> Unit
) {

    val infiniteTransition = rememberInfiniteTransition(label = "ghost_animation")
    val ghostOffsetY by infiniteTransition.animateFloat(
        initialValue = -20f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1500, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ), label = "ghost_offset_y"
    )

    val shadowOffsetY by infiniteTransition.animateFloat(
        initialValue = 20f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1500, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ), label = "shadow_offset_y"
    )

    val maxAlpha = 1f
    val minAlpha = 0.6f
    val maxOffset = 20f
    val shadowAlpha = lerp(
        start = maxAlpha,
        stop = minAlpha,
        fraction = abs(ghostOffsetY) / maxOffset
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AppBar()
        Spacer(Modifier.height(24.dp))
        AnimatedHocusPocusText()
        Spacer(Modifier.height(33.dp))
        Box(
            modifier = Modifier
                .graphicsLayer {
                    translationY = ghostOffsetY
                },
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier.size(244.dp),
                painter = painterResource(id = R.drawable.coffee_ghost1),
                contentDescription = "Ghost with coffee",
            )
        }

        Icon(
            modifier = Modifier
                .graphicsLayer {
                    translationY = shadowOffsetY
                }
                .alpha(shadowAlpha)
                .blur(12.dp),
            painter = painterResource(R.drawable.shadow),
            contentDescription = "shadow",
            tint = Color(0xFF1F1F1F),
        )
        Spacer(Modifier.weight(1f))
        ButtonWithText(
            modifier = Modifier.padding(top = 20.dp, bottom = 30.dp),
            text = "bring my coffee",
            icon = painterResource(R.drawable.coffee_cup),
            onClick = onClick
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    WelcomeScreen({})
}