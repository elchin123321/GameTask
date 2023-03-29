package com.example.gametask.ui

import com.example.gametask.data.firebase.Config

sealed interface AppUiState {
    data class Success(val config: Config) : AppUiState
    object Loading : AppUiState
    object Error : AppUiState
}