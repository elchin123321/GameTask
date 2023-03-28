package com.example.gametask.di

import com.example.gametask.data.ResourceProvider
import com.example.gametask.data.guess_game.GuessGameRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface AppBindModule {
    @Binds
    fun bindGuessGameRepository(
        repository: GuessGameRepository.Base
    ):GuessGameRepository

    @Binds
    fun bindResourceProvider(
        resourceProvider: ResourceProvider.Base,
    ):ResourceProvider

}
