package com.example.gametask.ui.theme

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.view.ViewGroup
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.viewinterop.AndroidView
import com.example.gametask.ui.ErrorScreen

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebViewScreen(
    url: String,
) {
    val backEnabled = remember { mutableStateOf(false) }
    var webView: WebView? = null
    val errorHandler = remember { mutableStateOf(false) }
    BackHandler {
        webView?.goBack()
    }
    if (!errorHandler.value) {
        AndroidView(
            factory = {
                WebView(it).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                    settings.javaScriptEnabled = true
                    webViewClient = object : WebViewClient() {
                        override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
                            super.onPageStarted(view, url, favicon)
                            backEnabled.value = view.canGoBack()
                        }

                        override fun onReceivedError(
                            view: WebView?,
                            request: WebResourceRequest?,
                            error: WebResourceError?
                        ) {
                            super.onReceivedError(view, request, error)
                            errorHandler.value = true
                        }
                    }
                    loadUrl(url)
                    webView = this

                }
            },
            update = {
                webView = it
            }
        )
    }else{
        ErrorScreen(retryClick = {errorHandler.value = false})
    }
}
