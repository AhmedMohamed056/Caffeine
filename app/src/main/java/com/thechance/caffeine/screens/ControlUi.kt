import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.thechance.caffeine.R
import com.thechance.caffeine.composable.ButtonWithText
import com.thechance.caffeine.screens.CoffeeStrength
import com.thechance.caffeine.screens.CupSize
import com.thechance.caffeine.screens.SizeSelector
import com.thechance.caffeine.screens.StrengthSelector

@Composable
fun ControlsUI(
    selectedSize: CupSize,
    selectedStrength: CoffeeStrength,

    onSizeSelected: (CupSize) -> Unit,
    onStrengthSelected: (CoffeeStrength) -> Unit,
    onContinueClicked: () -> Unit,

    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SizeSelector(
            selectedSize = selectedSize,
            onSizeSelected = onSizeSelected
        )

        Spacer(modifier = Modifier.height(32.dp))

        StrengthSelector(
            selectedStrength = selectedStrength,
            onStrengthSelected = onStrengthSelected
        )

        Spacer(Modifier.weight(1f))

        ButtonWithText(
            modifier = Modifier.padding(bottom = 50.dp),
            text = "Continue",
            icon = painterResource(R.drawable.arrow_right_coffe),
            onClick = onContinueClicked
        )
    }
}