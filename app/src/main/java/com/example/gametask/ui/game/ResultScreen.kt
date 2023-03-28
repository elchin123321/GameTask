package com.example.gametask.ui.game

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.gametask.R

@Composable
fun ResultScreen(
    uiState: UiState,
    onTryAgainButtonClick: () -> Unit,
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    BackHandler {
        onBackPressed()
    }
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(id = R.string.congratulations, uiState.generatedNumber))
        Text(text = stringResource(id = R.string.took_tries, uiState.numberOfTries))
        Button(onClick = { onTryAgainButtonClick() }) {
            Text(text = stringResource(id = R.string.try_again))
        }
    }
}