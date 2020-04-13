package com.gerry.wanandroidmvvm

import android.app.Application
import android.content.Context
import kotlin.properties.Delegates

class WanApplication : Application() {
    companion object {
        var CONTEXT: Context by Delegates.notNull()
//        lateinit var CURRENT_USER: User
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
    }

    override fun onCreate() {
        super.onCreate()

        CONTEXT = applicationContext
    }


}