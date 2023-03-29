package com.example.gametask.ui.game

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.example.gametask.R

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun GuessNumberScreen(
    uiState: UiState,
    onGuessNumber: (number: Int) -> Boolean,
    onNumberGuessed: () -> Unit,
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    BackHandler {
        onBackPressed()
    }
    val keyboardController = LocalSoftwareKeyboardController.current
    var numberInput by rememberSaveable {
        mutableStateOf("")
    }
    var isError by rememberSaveable {
        mutableStateOf(false)
    }


    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = uiState.resultText)
        Spacer(modifier = Modifier.weight(1f))
        OutlinedTextField(
            value = numberInput,
            onValueChange = {
                isError = false
                numberInput = it
            },
            label = { Text(text = stringResource(id = R.string.input_number)) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.NumberPassword,
                imeAction = ImeAction.Done,
            ),
            isError = isError,
            singleLine = true
        )
        if (isError) {
            Text(
                text = stringResource(id = R.string.incorrect_number),
                color = MaterialTheme.colors.error
            )
        }

        Button(onClick = {

            try {
                val number = numberInput.toInt()
                isError = false
                if (onGuessNumber(number)) {
                    keyboardController?.hide()
                    onNumberGuessed()
                }
                numberInput = ""
            } catch (e: NumberFormatException) {
                isError = true
            }

        }) {
            Text(text = stringResource(id = R.string.guess))
        }
        Spacer(modifier = Modifier.weight(1f))
    }

}