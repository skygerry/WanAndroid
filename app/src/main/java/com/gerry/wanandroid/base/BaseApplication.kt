package com.gerry.wanandroid.base

import android.app.Application
import android.util.Log
import com.gerry.wanandroid.http.BaseRetrofit
import com.tencent.smtt.sdk.QbSdk

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        BaseRetrofit.getInstance()?.init()

        //搜集本地tbs内核信息并上报服务器，服务器返回结果决定使用哪个内核。
        var cb = object : QbSdk.PreInitCallback {
            override fun onViewInitFinished(p0: Boolean) {
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                Log.d("app", " onViewInitFinished is $p0")
            }

            override fun onCoreInitFinished() {
            }
        }
        //x5内核初始化接口
        QbSdk.initX5Environment(applicationContext, cb)

    }
}