package com.example.gametask.ui.game

import androidx.lifecycle.ViewModel
import com.example.gametask.R
import com.example.gametask.data.ResourceProvider
import com.example.gametask.data.guess_game.Difficulty
import com.example.gametask.data.guess_game.GuessGameRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class GuessNumberViewModel @Inject constructor(
    private val repository: GuessGameRepository,
    private val resourceProvider: ResourceProvider
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()


    fun startGame(difficulty: Difficulty) {
        _uiState.update { currentState ->
            currentState.copy(
                generatedNumber = repository.generateNumber(difficulty),
                resultText = resourceProvider.string(
                    R.string.pick_number,
                    difficulty.from,
                    difficulty.to
                )
            )
        }
    }

    fun guessNumber(number: Int):Boolean {
        var result = false
        _uiState.update { currentState ->
            currentState.copy(
                numberOfTries = currentState.numberOfTries + 1,
                resultText = if (currentState.generatedNumber > number) {
                    resourceProvider.string(R.string.guess_low, number)
                } else if (currentState.generatedNumber < number) {
                    resourceProvider.string(R.string.guess_high, number)
                } else {
                    result = true
                    resourceProvider.string(R.string.you_win, number)
                }
            )
        }
        return result
    }
    fun reset(){
        _uiState.value = UiState()
    }


}