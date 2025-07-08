package com.thechance.caffeine.screens

import ControlsUI
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thechance.caffeine.R
import kotlinx.coroutines.delay


enum class CupSize(val scale: Float, val volumeText: String, val displayName: String) {
    SMALL(0.65f, "150 ML", "S"),
    MEDIUM(0.8f, "200 ML", "M"),
    LARGE(1f, "400 ML", "L")
}

enum class CoffeeStrength(val displayName: String, val targetAlpha: Float) {
    LOW("Low", 0.4f),
    MEDIUM("Medium", 0.6f),
    HIGH("High", 1.0f)
}

@Composable
fun CoffeeOrderScreen(
    drink: CoffeeDrink,
    onClickBack: () -> Unit,
    onNavigateToProcessing: () -> Unit
) {
    var selectedSize by remember { mutableStateOf(CupSize.MEDIUM) }
    var selectedStrength by remember { mutableStateOf(CoffeeStrength.MEDIUM) }
    var animatedStrengths by remember { mutableStateOf(emptySet<CoffeeStrength>()) }
    var isProcessing by remember { mutableStateOf(false) }

    val cupScale by animateFloatAsState(
        targetValue = selectedSize.scale,
        animationSpec = tween(durationMillis = 400),
        label = "cupScaleAnimation"
    )
    if (isProcessing) {
        LaunchedEffect(key1 = Unit) {
            delay(3600L)
            onNavigateToProcessing()
        }
    }
    Box(
        modifier = Modifier.background(Color.White)
    ) {
        Box(
            modifier = Modifier.background(Color.White)
        ) {
            AnimatedVisibility(
                visible = !isProcessing,
                exit = slideOutVertically(targetOffsetY = { -it }) + fadeOut()
            ) {
                OrderingHeader(drink, onClickBack)
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 100.dp)
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = selectedSize.volumeText,
                    color = Color(0x99000000),
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .padding(start = 24.dp, top = 40.dp)
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    CoffeeStrength.values().forEach { strength ->
                        AnimatedCoffeeBeans(
                            isVisible = strength in animatedStrengths,
                            strength = strength
                        )
                    }
                    Image(
                        painter = painterResource(id = drink.emptyCupImageRes),
                        contentDescription = "Coffee Cup",
                        modifier = Modifier
                            .size(width = 200.dp, height = 244.dp)
                            .scale(cupScale)
                    )

                    Image(
                        painter = painterResource(id = R.drawable.coffee_bean_icon),
                        contentDescription = "Coffee Cup",
                        modifier = Modifier
                            .size(66.dp)
                            .scale(cupScale)
                    )
                }
            }
            Crossfade(targetState = isProcessing, label = "Content Fader") { processing ->
                if (!processing) {
                    ControlsUI(
                        modifier = Modifier.weight(1f),
                        selectedSize = selectedSize,
                        onSizeSelected = { newSize -> selectedSize = newSize },
                        selectedStrength = selectedStrength,
                        onStrengthSelected = { newStrength ->
                            if (newStrength == selectedStrength) {
                            } else {
                                selectedStrength = newStrength
                                val current = animatedStrengths
                                animatedStrengths = if (newStrength in current) {
                                    current - newStrength
                                } else {
                                    current + newStrength
                                }
                            }
                        },
                        onContinueClicked = {
                            isProcessing = true
                        }
                    )
                } else {
                    AlmostDone(
                        modifier = Modifier.weight(1f),
                    )
                }
            }
        }
    }

}

// --- 3. مكون الأنيميشن الخاص بحبوب القهوة ---

@Composable
fun AnimatedCoffeeBeans(
    isVisible: Boolean,
    strength: CoffeeStrength
) {
    val transition = updateTransition(targetState = isVisible, label = "BeansTransition")

    // --- (الحل) تعريف مواصفات حركة موحدة ---
    val animationSpec: FiniteAnimationSpec<Float> = spring(
        dampingRatio = 0.6f,
        stiffness = Spring.StiffnessVeryLow
    )
    val animationSpecDp: FiniteAnimationSpec<Dp> = spring(
        dampingRatio = 0.6f,
        stiffness = Spring.StiffnessVeryLow
    )

    // --- تطبيق المواصفات الموحدة على كل الخصائص ---

    val scale by transition.animateFloat(
        label = "BeansScale",
        transitionSpec = { animationSpec } // استخدام المواصفات الموحدة
    ) { visible ->
        if (visible) 0.8f else 1.5f
    }

    val yOffset by transition.animateDp(
        label = "BeansYOffset",
        transitionSpec = { animationSpecDp } // استخدام المواصفات الموحدة
    ) { visible ->
        if (visible) 0.dp else (-300).dp
    }

    val alpha by transition.animateFloat(
        label = "BeansAlpha",
        transitionSpec = { animationSpec } // استخدام المواصفات الموحدة
    ) { visible ->
        if (visible) strength.targetAlpha else 0f
    }

    Image(
        painter = painterResource(id = R.drawable.coffe_droping),
        contentDescription = "Animated Coffee Beans",
        modifier = Modifier
            .size(100.dp)
            .graphicsLayer {
                this.scaleX = scale
                this.scaleY = scale
                this.translationY = yOffset.toPx()
                this.alpha = alpha
            }
    )
}


// --- 4. مكونات الاختيار ---

@Composable
fun SizeSelector(
    selectedSize: CupSize,
    onSizeSelected: (CupSize) -> Unit
) {
    val options = CupSize.values()

    Box(
        modifier = Modifier
            .width(152.dp)
            .height(56.dp)
            .clip(RoundedCornerShape(28.dp))
            .background(Color(0xFFF5F5F5)),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            options.forEach { size ->
                val isSelected = (selectedSize == size)

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) { onSizeSelected(size) },
                    contentAlignment = Alignment.Center
                ) {
                    val textColor by animateColorAsState(
                        targetValue = if (isSelected) Color(0xDEFFFFFF) else Color(0x991F1F1F),
                        animationSpec = tween(durationMillis = 200),
                        label = "TextColor"
                    )

                    val alpha by animateFloatAsState(
                        targetValue = if (isSelected) 1f else 0f,
                        animationSpec = if (isSelected) {
                            tween(durationMillis = 1000, delayMillis = 100)
                        } else {
                            tween(durationMillis = 1000)
                        },
                        label = "IndicatorAlpha"
                    )

                    Box(
                        modifier = Modifier
                            .alpha(alpha)
                            .shadow(elevation = 8.dp, shape = CircleShape)
                            .size(40.dp)
                            .background(
                                color = Color(0xFF7C351B),
                                shape = CircleShape
                            )
                    )

                    Text(
                        text = size.displayName,
                        color = textColor,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
fun StrengthSelector(
    selectedStrength: CoffeeStrength,
    onStrengthSelected: (CoffeeStrength) -> Unit
) {
    val options = CoffeeStrength.values()

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .width(152.dp)
                .height(56.dp)
                .clip(RoundedCornerShape(28.dp))
                .background(Color(0xFFF5F5F5)),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                options.forEach { strength ->
                    val isSelected = (selectedStrength == strength)

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null
                            ) { onStrengthSelected(strength) },
                        contentAlignment = Alignment.Center
                    ) {
                        val alpha by animateFloatAsState(
                            targetValue = if (isSelected) 1f else 0f,
                            animationSpec = if (isSelected) {
                                tween(durationMillis = 1000, delayMillis = 100)
                            } else {
                                tween(durationMillis = 1000)
                            },
                            label = "StrengthIndicatorAlpha"
                        )

                        Box(
                            modifier = Modifier
                                .alpha(alpha)
                                .shadow(elevation = 8.dp, shape = CircleShape)
                                .size(40.dp)
                                .background(
                                    color = Color(0xFF7C351B),
                                    shape = CircleShape
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.coffee_beans),
                                contentDescription = null,
                                tint = Color(0xDEFFFFFF),
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.width(152.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            options.forEach { strength ->
                Text(
                    text = strength.displayName,
                    color = Color(0x991F1F1F),
                    fontSize = 10.sp,
                )
            }
        }
    }
}


// --- للعرض في Android Studio Preview ---
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewCoffeeOrderScreen() {
    CoffeeOrderScreen(drink = coffeeDrinks[0], {}, {})
}