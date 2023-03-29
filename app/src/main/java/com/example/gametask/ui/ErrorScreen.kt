package com.example.gametask.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.gametask.R

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier,
    retryClick: () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.devise_is_offline),
            style = MaterialTheme.typography.h3,
            textAlign = TextAlign.Center
        )
        Button(
            onClick = retryClick
        ) {
            Text(text = stringResource(id = R.string.retry))
        }
    }
}