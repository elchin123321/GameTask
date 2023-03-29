package com.example.gametask.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gametask.data.firebase.RemoteConfigDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AppViewModel @Inject constructor(private val remoteConfig: RemoteConfigDataSource) :
    ViewModel() {

    var uiState: AppUiState by mutableStateOf(AppUiState.Loading)
        private set

    init {
        fetchConfig()
    }

    fun fetchConfig() {
        uiState = AppUiState.Loading
        viewModelScope.launch {
            delay(1000)
            try {
                uiState = AppUiState.Success(remoteConfig.getConfig())
            } catch (e: java.lang.Exception) {
                uiState = AppUiState.Error
            }

        }
    }
}