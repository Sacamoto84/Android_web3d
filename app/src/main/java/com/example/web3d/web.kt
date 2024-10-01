package com.example.web3d

import android.annotation.SuppressLint
import android.content.Context
import android.net.http.SslError
import android.view.View
import android.webkit.CookieManager
import android.webkit.JsResult
import android.webkit.SslErrorHandler
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.LinearLayout
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "SetJavaScriptEnabled")
@Composable
fun web(ip: String) {

  //  val cookieManager: CookieManager = CookieManager.getInstance()

//    cookieManager.setAcceptCookie(true)
//
//    // Добавляем куки в заголовки HTTP-запросов
//    val cookieString = "cookie_name=cookie_value"
//    cookieManager.setCookie(ip, cookieString)

    // Set up back navigation
    val onBackPressedDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher

    var canGoBack by remember { mutableStateOf(false) }

    val context = LocalContext.current

    val babView = remember {
        getWebView(context, ip)
    }

    // Check if WebView can go back
    DisposableEffect(babView) {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                canGoBack = babView.canGoBack()
            }
        }

        onBackPressedDispatcher?.addCallback(callback)

        onDispose {
            callback.remove()
        }
    }

    // Handle back navigation
    DisposableEffect(canGoBack) {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                babView.goBack()
            }
        }
        onBackPressedDispatcher?.addCallback(callback)
        onDispose {
            callback.remove()
        }
    }

        AndroidView(modifier = Modifier
            .fillMaxSize(),
            factory = { babView },
            update = { webView -> // Update code here if needed

            })




}

val webChromeClient1 = object : WebChromeClient() {

    override fun onJsAlert(
        view: WebView, url: String, message: String, result: JsResult
    ): Boolean {
        return super.onJsAlert(view, url, message, result)
    }

    override fun onShowCustomView(view: View, callback: CustomViewCallback) {
        callback.onCustomViewHidden()
    }

    override fun onHideCustomView() {
        super.onHideCustomView()
    }

}


@SuppressLint("StaticFieldLeak")
var babView: WebView? = null

fun getWebView(context: Context, ip: String): WebView {

    if (babView == null) {

        babView = WebView(context).apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT
            )


            // Настраиваем User-Agent для десктопной версии
            settings.userAgentString = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3"

            settings.javaScriptEnabled = true
            settings.setRenderPriority(WebSettings.RenderPriority.HIGH)
            settings.useWideViewPort = true
            settings.loadWithOverviewMode = true
            settings.domStorageEnabled = true
            settings.loadsImagesAutomatically = true
            settings.allowFileAccess = true
            //settings.allowFileAccessFromFileURLs = true
            //settings.allowUniversalAccessFromFileURLs = true
            settings.allowContentAccess = true

            settings.javaScriptCanOpenWindowsAutomatically = true

            settings.setSupportZoom(true)

            settings.builtInZoomControls = true
            settings.displayZoomControls = false

            setInitialScale(100) // Масштаб 100% как на десктопе

            settings.pluginState = WebSettings.PluginState.ON // settings.setSupportMultipleWindows(true)
            settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW //??

            webViewClient = object : WebViewClient() {

                @SuppressLint("WebViewClientOnReceivedSslError")
                override fun onReceivedSslError(
                    view: WebView?, handler: SslErrorHandler, error: SslError?
                ) {
                    handler.proceed()
                }

            }

            //webChromeClient = webChromeClient1 //WebChromeClient() //loadUrl(ip)

            loadUrl(ip)
        }
        return babView!!
    } else {
        return babView!!
    }

}

