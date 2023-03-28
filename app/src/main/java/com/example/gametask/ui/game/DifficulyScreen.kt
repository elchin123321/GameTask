package com.example.gametask.ui.game

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.gametask.R
import com.example.gametask.data.guess_game.Difficulty

@Composable
fun DifficultyScreen(
    onDifficultyChoose: (difficulty: Difficulty) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.choose_difficulty),
            style = MaterialTheme.typography.h2,
            textAlign = TextAlign.Center,
            modifier = modifier.fillMaxWidth(),
        )
        Spacer(modifier = modifier.weight(1f))
        for (difficulty in Difficulty.values()) {
            Button(
                onClick = {
                    onDifficultyChoose(difficulty)
                },
                modifier = Modifier.fillMaxWidth(fraction = 0.8f)
            ) {
                Text(
                    text = difficulty.name,
                    style = MaterialTheme.typography.button
                )
            }
        }
        Spacer(modifier = modifier.weight(1f))
    }
}