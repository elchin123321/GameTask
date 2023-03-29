package com.example.gametask.ui.game

import androidx.compose.foundation.layout.padding
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
        startDestination = GameScreens.DifficultyScreen.name,
        modifier = modifier.padding()
    ) {
        composable(route = GameScreens.DifficultyScreen.name) {
            DifficultyScreen(onDifficultyChoose = { difficulty ->
                viewModel.startGame(difficulty)
                navController.navigate(GameScreens.GuessNumberScreen.name)
            })
        }
        composable(route = GameScreens.GuessNumberScreen.name) {
            GuessNumberScreen(
                uiState = uiState,
                onGuessNumber = { number ->
                    viewModel.guessNumber(number)
                },
                onNumberGuessed = {
                    navController.navigate(GameScreens.ResultScreen.name)
                },
                onBackPressed = {
                    viewModel.reset()
                    navController.popBackStack(
                        GameScreens.DifficultyScreen.name,
                        inclusive = false
                    )
                }
            )
        }
        composable(route = GameScreens.ResultScreen.name) {
            ResultScreen(
                uiState,
                onTryAgainButtonClick = {
                    viewModel.reset()
                    navController.popBackStack(
                        GameScreens.DifficultyScreen.name,
                        inclusive = false
                    )
                },
                onBackPressed = {
                    viewModel.reset()
                    navController.popBackStack(
                        GameScreens.DifficultyScreen.name,
                        inclusive = false
                    )
                }
            )
        }
    }

}







