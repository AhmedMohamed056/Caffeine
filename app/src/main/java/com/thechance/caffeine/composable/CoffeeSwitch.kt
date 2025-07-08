import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thechance.caffeine.R

@Composable
fun CoffeeSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    val trackOnColor = Color(0xFF7C351B)
    val trackOffColor = Color(0xFFFFEEE7)

    val trackColor by animateColorAsState(
        targetValue = if (checked) trackOnColor else trackOffColor,
        animationSpec = tween(500),
        label = "trackColor"
    )

    val horizontalBias by animateFloatAsState(
        targetValue = if (checked) -1f else 1f,
        animationSpec = tween(500),
        label = "thumbBias"
    )

    val thumbAlignment = BiasAlignment(horizontalBias = horizontalBias, verticalBias = 0f)


    val rotation by animateFloatAsState(
        targetValue = if (checked) 0f else 120f,
        animationSpec = tween(500),
        label = "rotation"
    )

    Box(
        modifier = modifier
            .width(78.dp)
            .height(40.dp)
            .clip(CircleShape)
            .background(trackColor)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = { onCheckedChange(!checked) }
            ),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            Text(
                "OFF",
                color = Color(0x99000000),
                fontWeight = FontWeight.Bold,
                fontSize = 10.sp,
                fontFamily = FontFamily(
                    Font(R.font.urbanist_bold, FontWeight.Bold)
                ),
            )
            Text(
                "ON",
                color = Color(0x99FFFFFF),
                fontWeight = FontWeight.Bold,
                fontSize = 10.sp,
                fontFamily = FontFamily(
                    Font(R.font.urbanist_bold, FontWeight.Bold)
                ),
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(1.dp),
            contentAlignment = thumbAlignment
        ) {
                Image(
                    painter = painterResource(id = R.drawable.coffee_foam_texture),
                    contentDescription = "Switch Thumb",
                    modifier = Modifier
                        .graphicsLayer { rotationZ = rotation }
                        .size(40.dp)
                        .clip(CircleShape)
                )
        }
    }
}