package com.example.gametask.ui.game

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun GameScreen(
    modifier: Modifier = Modifier,
    viewModel: GuessNumberViewModel = viewModel(),
) {
    val navController = rememberNavController()
    val uiState by viewModel.uiState.collectAsState()

    NavHost(
        navController = navController,
        startDestination = GameDestinations.DifficultyScreen.name,
        modifier = modifier
    ) {
        composable(route = GameDestinations.DifficultyScreen.name) {
            DifficultyScreen(onDifficultyChoose = { difficulty ->
                viewModel.startGame(difficulty)
                navController.navigate(GameDestinations.GuessNumberScreen.name)
            })
        }
        composable(route = GameDestinations.GuessNumberScreen.name) {
            GuessNumberScreen(
                uiState = uiState,
                onGuessNumber = { number ->
                    viewModel.guessNumber(number)
                },
                onNumberGuessed = {
                    navController.navigate(GameDestinations.ResultScreen.name)
                },
                onBackPressed = {
                    viewModel.reset()
                    navController.popBackStack(GameDestinations.DifficultyScreen.name, inclusive = false)
                }
            )
        }
        composable(route = GameDestinations.ResultScreen.name) {
            ResultScreen(
                uiState,
                onTryAgainButtonClick = {
                    viewModel.reset()
                    navController.popBackStack(GameDestinations.DifficultyScreen.name, inclusive = false)
                },
                onBackPressed = {
                    viewModel.reset()
                    navController.popBackStack(GameDestinations.DifficultyScreen.name, inclusive = false)
                }
            )
        }
    }
}






