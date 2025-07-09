package com.thechance.caffeine.screens

import CoffeeSwitch
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thechance.caffeine.R
import com.thechance.caffeine.composable.ButtonWithText

@Composable
fun CoffeeReadyScreen(
    drink: CoffeeDrink,
    onContinueClicked: () -> Unit,
    onBackClicked: () -> Unit
) {
    val animationState = remember { MutableTransitionState(false) }

    var isTakeAway by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        animationState.targetState = true
    }

    val transition = updateTransition(animationState, label = "ReadyScreenTransition")

    val initialYOffset = (-350).dp

    val topSectionY by transition.animateDp(
        label = "TopOffset",
        transitionSpec = { tween(durationMillis = 800, delayMillis = 200) }
    ) { state ->
        if (state) (-50).dp else initialYOffset
    }

    val bottomButtonY by transition.animateDp(
        label = "BottomOffset",
        transitionSpec = { tween(durationMillis = 800, delayMillis = 200) }
    ) { state ->
        if (state) 0.dp else -initialYOffset
    }

    val contentAlpha by transition.animateFloat(
        label = "ContentAlpha",
        transitionSpec = { tween(durationMillis = 1000, delayMillis = 300) }
    ) { state ->
        if (state) 1f else 0f
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .offset(y = 50.dp)
                .alpha(contentAlpha),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier
                        .size(width = 180.dp, height = 230.dp),
                    painter = painterResource(id = drink.emptyCupImageRes),
                    contentDescription = "Cup Body"
                )
                Image(
                    painter = painterResource(id = R.drawable.coffee_bean_icon),
                    contentDescription = "Coffee Cup",
                    modifier = Modifier
                        .size(64.dp)
                )
            }
            Spacer(modifier = Modifier.height(28.dp))
            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CoffeeSwitch(
                    checked = isTakeAway,
                    onCheckedChange = { isTakeAway = it },
                )
                Text(
                    text = "Take Away",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily(
                        Font(R.font.urbanist_bold, FontWeight.Bold)
                    ),
                    color = Color(0xB21F1F1F)
                )
            }
        }

        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .offset(y = topSectionY),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(60.dp))
            Icon(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .background(
                        color = Color(0xFFF5F5F5),
                        shape = CircleShape
                    )
                    .padding(12.dp)
                    .align(Alignment.Start)
                    .clip(CircleShape)
                    .clickable(onClick = onBackClicked),
                painter = painterResource(R.drawable.ic_close),
                contentDescription = null,
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .background(Color(0xFF7C351B), shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_check),
                    contentDescription = "Ready",
                    tint = Color(0xDEFFFFFF)
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Your coffee is ready,\nEnjoy",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = Color(0xDE1F1F1F),
                fontFamily = FontFamily(
                    Font(R.font.urbanist_bold, FontWeight.Bold)
                ),
            )
            Spacer(modifier = Modifier.height(17.dp))
            Image(
                modifier = Modifier
                    .size(width = 200.dp, height = 54.dp),
                painter = painterResource(id = drink.cupCover),
                contentDescription = "Cup Lid"
            )
        }

        ButtonWithText(
            modifier = Modifier
                .padding(top = 20.dp, bottom = 30.dp)
                .align(Alignment.BottomCenter)
                .offset(y = bottomButtonY),
            text = "Continue",
            icon = painterResource(R.drawable.arrow_right_coffe),
            onClick = onContinueClicked
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCoffeeReadyScreen() {
    CoffeeReadyScreen(drink = coffeeDrinks[0], {},{})
}