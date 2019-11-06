package com.gerry.wanandroid.base

import android.app.Application
import com.franmontiel.persistentcookiejar.ClearableCookieJar
import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import com.gerry.wanandroid.http.BaseRetrofit

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        BaseRetrofit.getInstance()?.init(this)
    }
}