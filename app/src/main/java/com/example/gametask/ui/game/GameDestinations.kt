package com.example.gametask.ui.game

import androidx.annotation.StringRes
import com.example.gametask.R

enum class GameDestinations(@StringRes val title:Int) {
    DifficultyScreen(R.string.difficulty_screen),
    GuessNumberScreen(R.string.guess_number_screen),
    ResultScreen(R.string.result_screen)
}