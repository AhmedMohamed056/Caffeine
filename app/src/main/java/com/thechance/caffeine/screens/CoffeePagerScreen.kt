package com.thechance.caffeine.screens

import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import com.thechance.caffeine.R
import com.thechance.caffeine.composable.AppBar
import com.thechance.caffeine.composable.ButtonWithText
import kotlin.math.absoluteValue

data class CoffeeDrink(
    val name: String,
    val imageRes: Int,
    val emptyCupImageRes: Int,
    val cupCover: Int
    )

val coffeeDrinks = listOf(
    CoffeeDrink("Espresso", R.drawable.coffee_espresso,R.drawable.espresso_empty_cup,R.drawable.espresso_cover),
    CoffeeDrink("Macchiato", R.drawable.coffee_macchiato,R.drawable.macchiato_empty_cup,R.drawable.macchiato_cover),
    CoffeeDrink("Latte", R.drawable.coffee_latte,R.drawable.latte_empty_cup,R.drawable.lattee_cover), // استبدل الصور بصورك
    CoffeeDrink("Black", R.drawable.coffee_black, R.drawable.black_empty_cup,R.drawable.black_cover)
)

@Composable
fun CoffeePagerScreen(
    onClick: (CoffeeDrink) -> Unit
) {
    val initialPageIndex = coffeeDrinks.indexOfFirst { it.name == "Black" }.coerceAtLeast(0)
    val pagerState = rememberPagerState(initialPage = initialPageIndex) {
        coffeeDrinks.size
    }

    val customFlingBehavior = PagerDefaults.flingBehavior(
        state = pagerState,
        snapAnimationSpec = tween(durationMillis = 400)
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AppBar()
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 16.dp, bottom = 56.dp),
            horizontalAlignment = Alignment.Start,
        ) {
            Text(
                text = "Good Morning",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(
                    Font(R.font.urbanist_bold, FontWeight.Bold)
                ),
                color = Color(0xFFB3B3B3)
            )
            Text(
                text = "Hamsa ☀",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(
                    Font(R.font.urbanist_bold, FontWeight.Bold)
                ),
                color = Color(0xFF3B3B3B)
            )
            Text(
                text = "What would you like to drink today?",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(
                    Font(R.font.urbanist_bold, FontWeight.Bold)
                ),
                color = Color(0xCC1F1F1F)
            )
        }
        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 100.dp),
            pageSpacing = 36.dp,
            flingBehavior = customFlingBehavior
        ) { page ->
            PagerItem(
                drink = coffeeDrinks[page],
                pagerState = pagerState,
                page = page
            )
        }
        Spacer(Modifier.weight(1f))
        ButtonWithText(
            modifier = Modifier.padding(bottom = 50.dp),
            text = "Continue",
            icon = painterResource(R.drawable.arrow_right_coffe),
            onClick = {
                val selectedDrink = coffeeDrinks[pagerState.currentPage]
                onClick(selectedDrink)
            }
        )
    }
}

@Composable
fun PagerItem(drink: CoffeeDrink, pagerState: PagerState, page: Int) {
    val pageOffset = (
            (pagerState.currentPage - page) + pagerState.currentPageOffsetFraction
            ).absoluteValue

    val scale = lerp(
        start = 1f,
        stop = 0.65f,
        fraction = pageOffset
    )

    val translationY = lerp(
        start = 0f,
        stop = 100f,
        fraction = pageOffset
    )

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    )
    {

        Image(
            modifier = Modifier
                .graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                    this.translationY = translationY
                }
                .size(width = 199.dp, height = 244.dp),
            painter = painterResource(id = drink.imageRes),
            contentDescription = drink.name,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = drink.name,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCoffeePagerScreen() {
    CoffeePagerScreen({})
}