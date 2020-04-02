package com.gerry.wanandroid.web

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import kotlinx.android.synthetic.main.activity_comment_web.*


class CommentWebActivity : AppCompatActivity() {
    private var mHomeUrl = ""
    private var titleList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.gerry.wanandroid.R.layout.activity_comment_web)
        initHardwareAccelerate()

        if (intent != null) {
            if (intent.getStringExtra("url") != null) {
                mHomeUrl = intent.getStringExtra("url")
            }
        }

        initView()
    }

    /**
     * 启用硬件加速
     */
    private fun initHardwareAccelerate() {
        try {
            if (Build.VERSION.SDK_INT >= 11) {
                window
                    .setFlags(
                        android.view.WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                        android.view.WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED
                    )
            }
        } catch (e: Exception) {
        }

    }

    private fun initView() {


        comment_webView.addProgressBar()

        comment_webView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                if (newProgress == 100) {
                    comment_webView.mProgressBar.visibility = View.GONE
                } else {
                    comment_webView.visibility = View.VISIBLE
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        comment_webView.mProgressBar.setProgress(newProgress, true)
                    } else {
                        comment_webView.mProgressBar.progress = newProgress
                    }
                }
            }

            override fun onReceivedTitle(view: WebView?, title: String?) {
                comment_title_tv.text = title
                titleList.add(title!!)
            }

        }
        comment_webView.loadUrl(mHomeUrl)

        comment_back_iv.setOnClickListener {
            goBack()
        }
    }

    /**
     * 返回
     */
    private fun goBack() {
        val canGoBack = comment_webView?.canGoBack() ?: false
        if (canGoBack) {
            comment_webView?.goBack()
            titleList.removeAt(titleList.lastIndex)
            comment_title_tv.text = titleList[titleList.lastIndex]
        } else {
            finish()
        }
    }


    override fun onBackPressed() {
        super.onBackPressed()
        goBack()
    }


    override fun onDestroy() {
        //释放资源
        if (comment_webView != null)
            comment_webView.destroy()
        super.onDestroy()
    }

}
