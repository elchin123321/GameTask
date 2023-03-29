package com.example.gametask.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gametask.data.firebase.Config
import com.example.gametask.ui.game.GameScreen
import com.example.gametask.ui.theme.WebViewScreen

@Composable
fun GameApp(
    viewModel: AppViewModel = viewModel(),
) {

    when (val uiState = viewModel.uiState.collectAsState().value) {
        is AppUiState.Loading -> SplashScreen()
        is AppUiState.Error -> ErrorScreen(
            retryClick = {
                viewModel.fetchConfig()
            }
        )
        is AppUiState.Success -> HomeScreen(
            config = uiState.config,
        )

    }
}

@Composable
fun HomeScreen(
    config: Config,
) {
    if (config.status) {
        WebViewScreen(
            url = config.link,
        )
    } else {
        GameScreen()
    }
}



