package com.thechance.caffeine.screens


import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.thechance.caffeine.R
import kotlin.math.absoluteValue

data class Snack(
    val id: Int,
    val name: String,
    val imageRes: Int,
    val snackImg: Int
)

val snacks = listOf(
    Snack(1, "Oreo", R.drawable.oreo, R.drawable.snack6),
    Snack(2, "Croissant", R.drawable.croissant, R.drawable.snack4),
    Snack(3, "Cinnamon Roll", R.drawable.cinnamon_roll, R.drawable.snack5),
    Snack(4, "Cookies", R.drawable.cookies, R.drawable.snack3),
    Snack(6, "Chocolate", R.drawable.chocolate, R.drawable.snack1),
    Snack(7, "Donut", R.drawable.donut, R.drawable.snack2),
)

@Composable
fun SnackSelectionScreen(
    onSnackClicked: (Snack) -> Unit
) {
    val listState = rememberLazyListState()

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            Icon(
                modifier = Modifier
                    .background(
                        color = Color(0xFFF5F5F5),
                        shape = CircleShape
                    )
                    .padding(12.dp),
                painter = painterResource(R.drawable.ic_close),
                contentDescription = null,
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.width(24.dp))
            Text(
                text = "Take your snack",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xDE1F1F1F),
                fontFamily = FontFamily(
                    Font(R.font.urbanist_bold, FontWeight.Bold)
                ),
            )
        }

        CurvedLazyColumn(
            items = snacks,
            listState = listState,
            modifier = Modifier.weight(1f),
            onSnackClicked = onSnackClicked
        )
    }
}

@Composable
fun CurvedLazyColumn(
    items: List<Snack>,
    listState: LazyListState,
    modifier: Modifier = Modifier,
    onSnackClicked: (Snack) -> Unit
) {
    LazyColumn(
        state = listState,
        modifier = modifier,
        contentPadding = PaddingValues(top = 150.dp, bottom = 50.dp),
        verticalArrangement = Arrangement.spacedBy((-190).dp)
    ) {
        items(items, key = { it.id }) { snack ->
            val (rotation, translationX, scale, zIndex) = getTransformations(listState, snack.id)
            SnackCard(
                snack = snack,
                rotation = rotation,
                translationX = translationX,
                scale = scale,
                zIndex = zIndex,
                onClick = {onSnackClicked(snack)}
            )
        }
    }
}

@Composable
private fun getTransformations(
    listState: LazyListState,
    itemId: Int
): Quadruple<Float, Float, Float, Float> {
    val visibleItemsInfo = listState.layoutInfo.visibleItemsInfo
    val itemInfo = visibleItemsInfo.find { it.key == itemId }

    if (itemInfo == null) {
        return Quadruple(0f, 0f, 0.6f, 0f)
    }

    val itemOffset = itemInfo.offset
    val itemHeight = itemInfo.size
    val viewportHeight = listState.layoutInfo.viewportSize.height
    val viewportCenter = viewportHeight / 3f
    val itemCenter = itemOffset + (itemHeight / 2f)

    val deltaFromCenter = (itemCenter - viewportCenter) / viewportCenter

    val maxRotation = 25f
    val maxTranslationX = -450f
    val minScale = 0.6f

    val rotation = maxRotation * deltaFromCenter
    val translationX = maxTranslationX * deltaFromCenter.absoluteValue
    val scale = 1f - ((1f - minScale) * deltaFromCenter.absoluteValue)

    val zIndex = 1f - deltaFromCenter.absoluteValue

    val animatedRotation by animateFloatAsState(
        targetValue = rotation,
        animationSpec = tween(300),
        label = ""
    )
    val animatedTranslationX by animateFloatAsState(
        targetValue = translationX,
        animationSpec = tween(300),
        label = ""
    )
    val animatedScale by animateFloatAsState(
        targetValue = scale,
        animationSpec = tween(300),
        label = ""
    )

    return Quadruple(animatedRotation, animatedTranslationX, animatedScale, zIndex)
}

data class Quadruple<A, B, C, D>(val first: A, val second: B, val third: C, val fourth: D)


@Composable
fun SnackCard(
    snack: Snack,
    rotation: Float,
    translationX: Float,
    scale: Float,
    zIndex: Float,
    onClick: () -> Unit
) {
    Image(
        painter = painterResource(id = snack.imageRes),
        contentDescription = snack.name,
        modifier = Modifier
            .width(300.dp)
            .height(300.dp)
            .zIndex(zIndex)
            .graphicsLayer {
                this.rotationZ = rotation
                this.translationX = translationX
                this.scaleX = scale
                this.scaleY = scale
            }
            .clickable(onClick = onClick)
    )
}