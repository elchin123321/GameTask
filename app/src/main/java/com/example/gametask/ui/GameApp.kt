package com.example.gametask.ui

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gametask.data.firebase.Config
import com.example.gametask.ui.game.GameScreen

@Composable
fun GameApp(
    modifier: Modifier = Modifier,
    viewModel: AppViewModel = viewModel(),
) {
    BackHandler {

    }
    val uiState = viewModel.uiState
    when(uiState){
        is AppUiState.Loading-> SplashScreen()
        is AppUiState.Error -> SplashScreen()
        is AppUiState.Success -> HomeScreen(config = uiState.config)

    }
}

@Composable
fun HomeScreen(
    config: Config,
    modifier: Modifier = Modifier,
){
    if(config.status){
        WebViewScreen(url = config.link)
    }else{
        GameScreen()
    }
}


@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebViewScreen(
    url:String,
    modifier: Modifier = Modifier
){
    AndroidView(
        factory = {
            WebView(it).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                settings.javaScriptEnabled = true
                webViewClient = object : WebViewClient(){}
                loadUrl(url)

            }
        },
        update = {
            it.loadUrl(url)
        }
    )
}