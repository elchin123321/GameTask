package com.example.gametask.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gametask.data.firebase.RemoteConfigManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AppViewModel @Inject constructor(
    private val remoteConfig: RemoteConfigManager,
) : ViewModel() {

    var uiState = MutableStateFlow<AppUiState>(AppUiState.Loading)
        private set

    init {
        fetchConfig()
    }

    fun fetchConfig() {
        uiState.value = AppUiState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            remoteConfig.getConfig(
                onSuccess = {config->
                    uiState.value = AppUiState.Success(config)
                },
                onFailure = {
                    uiState.value = AppUiState.Error
                }
            )

        }
    }


}