package com.gerry.wanandroid.web

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import android.util.AttributeSet
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import com.gerry.wanandroid.R
import com.gerry.wanandroid.utils.SizeUtils


class LoadingX5WebView : WebView {
    lateinit var mProgressBar: ProgressBar

    private val client = object : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
            view?.loadUrl(url)
            return true
        }

    }

    @SuppressLint("SetJavaScriptEnabled")
    constructor(arg0: Context, arg1: AttributeSet) : super(arg0, arg1) {
        this.webViewClient = client
        // this.setWebChromeClient(chromeClient);
        // WebStorage webStorage = WebStorage.getInstance();
        initWebViewSettings()
//        this.view.isClickable = true
    }

    private fun initWebViewSettings() {
        val settings = settings
        settings.javaScriptEnabled = true
        settings.setSupportZoom(true)
        settings.displayZoomControls = false
        settings.builtInZoomControls = true
        settings.allowFileAccess = true
        settings.javaScriptCanOpenWindowsAutomatically = true
        settings.cacheMode = WebSettings.LOAD_DEFAULT
        settings.domStorageEnabled = true
        settings.databaseEnabled = true
        settings.setAppCacheEnabled(true)
        settings.useWideViewPort = true
        settings.loadWithOverviewMode = true
        settings.loadsImagesAutomatically = true
        settings.blockNetworkImage = false
        settings.blockNetworkLoads = false
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        }
    }


    /**
     * 添加进度条
     */
    fun addProgressBar() {
        try {
            mProgressBar = ProgressBar(
                context, null,
                android.R.attr.progressBarStyleHorizontal
            )
            mProgressBar.layoutParams = LayoutParams(
                LayoutParams.MATCH_PARENT, SizeUtils.dp2px(context, 2f)
            )
            mProgressBar.progressDrawable = context.resources
                .getDrawable(R.drawable.drawable_webview_progress)
            addView(mProgressBar)//添加进度条至LoadingWebView中
            mProgressBar.visibility = View.VISIBLE
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 移除进度条
     */
    fun removeProgress() {}

    constructor(arg0: Context) : super(arg0) {
        setBackgroundColor(85621)
    }

}