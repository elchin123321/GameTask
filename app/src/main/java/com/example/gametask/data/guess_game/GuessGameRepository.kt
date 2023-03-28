package com.example.gametask.data.guess_game


import javax.inject.Inject
import kotlin.random.Random

interface GuessGameRepository {
    fun generateNumber(difficulty: Difficulty):Int

    class Base @Inject constructor():GuessGameRepository{
        override fun generateNumber(difficulty: Difficulty): Int {
            return when(difficulty){
                Difficulty.Easy -> Random.nextInt(difficulty.from,difficulty.to)
                Difficulty.Medium -> Random.nextInt(difficulty.from,difficulty.to)
                Difficulty.Hard -> Random.nextInt(difficulty.from,difficulty.to)
            }
        }



    }
}