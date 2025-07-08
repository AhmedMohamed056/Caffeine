package com.thechance.caffeine.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun HomeScreen() {
    var currentState by remember { mutableStateOf(HomeUIState.Welcome) }

    var selectedDrink by remember { mutableStateOf<CoffeeDrink?>(null) }

    var selectedSnack by remember { mutableStateOf<Snack?>(null) }

    when (currentState) {
        HomeUIState.Welcome -> {
            WelcomeScreen (
                onClick = {
                    currentState = HomeUIState.DrinkSelection
                }
            )
        }
        HomeUIState.DrinkSelection -> {
            CoffeePagerScreen(
                onClick = { drink ->
                    selectedDrink = drink
                    currentState = HomeUIState.DrinkCustomization
                }
            )
        }
        HomeUIState.DrinkCustomization -> {
            selectedDrink?.let { drink ->
                CoffeeOrderScreen(
                    drink = drink,
                    onClickBack = {
                        currentState = HomeUIState.DrinkSelection
                    },
                    onNavigateToProcessing = {
                        currentState = HomeUIState.Processing
                    },
                )
            }
        }


        HomeUIState.Processing -> {
            selectedDrink?.let { drink ->
                CoffeeReadyScreen(
                    drink = drink,
                    onContinueClicked = {
                        currentState = HomeUIState.SnackSelection
                    }
                )
            }
        }
        HomeUIState.SnackSelection -> {
            SnackSelectionScreen(
                onSnackClicked = { snack ->
                    selectedSnack = snack
                    currentState = HomeUIState.ThankYou
                },
            )
        }
        HomeUIState.ThankYou -> {
            selectedSnack?.let { snack ->
                ThankYouScreen(snack = snack)
            }
        }
    }
}