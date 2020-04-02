package com.gerry.wanandroid.http

import android.content.Context
import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import com.gerry.wanandroid.http.service.WanAndroidService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import okhttp3.logging.HttpLoggingInterceptor


class BaseRetrofit {
    private val DEFAULT_TIME_OUT = 10 //超时时间s
    private val DEFAULT_READ_TIME_OUT = 10 //读取时间s
    private val DEFAULT_WRITE_TIME_OUT = 10 //写入时间s

    companion object {
        private var mInstance: BaseRetrofit? = null
        private var retrofit: Retrofit? = null
        @Volatile
        private var request: WanAndroidService? = null

        fun getInstance(): BaseRetrofit? {
            if (mInstance == null) {
                synchronized(BaseRetrofit::class.java) {
                    if (mInstance == null) {
                        mInstance = BaseRetrofit()
                    }
                }
            }
            return mInstance
        }

        fun getRequest(): WanAndroidService? {
            if (request == null) {
                synchronized(WanAndroidService::class.java) {
                    request = retrofit!!.create(WanAndroidService::class.java!!)
                }
            }
            return request
        }
    }

    /**
     * 初始化必要对象和参数
     */
    fun init(context: Context) {
        val logInterceptor = HttpLoggingInterceptor()
        logInterceptor.level = (HttpLoggingInterceptor.Level.BODY)
        var cookieJar = PersistentCookieJar(SetCookieCache(), SharedPrefsCookiePersistor(context))
        // 初始化okhttp
        val client = OkHttpClient.Builder()
            .connectTimeout(DEFAULT_TIME_OUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(DEFAULT_READ_TIME_OUT.toLong(), TimeUnit.SECONDS)
            .writeTimeout(DEFAULT_WRITE_TIME_OUT.toLong(), TimeUnit.SECONDS)
            //错误重连
            .retryOnConnectionFailure(true)
            .cookieJar(cookieJar)
            .addInterceptor(logInterceptor)
            .build()

        // 初始化Retrofit
        retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl(WanAndroidService.HOST)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


}