package com.gerry.wanandroidmvvm

import android.app.Application
import android.content.Context
import kotlin.properties.Delegates

class WanApplication : Application() {
    companion object {
        var CONTEXT: Context by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()

        CONTEXT = applicationContext
    }


}