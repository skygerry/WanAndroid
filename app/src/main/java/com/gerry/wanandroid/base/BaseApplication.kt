package com.gerry.wanandroid.base

import android.app.Application
import com.gerry.wanandroid.http.BaseRetrofit

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        BaseRetrofit.getInstance()?.init()
    }
}