package com.gerry.basemvvm.network

import com.gerry.basemvvm.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

abstract class BaseRetrofit {
    companion object {
        private const val DEFAULT_TIME_OUT = 10 //超时时间s
        private const val DEFAULT_READ_TIME_OUT = 10 //读取时间s
        private const val DEFAULT_WRITE_TIME_OUT = 10 //写入时间s
    }

    private val client: OkHttpClient
        get() {
            val builder = OkHttpClient.Builder()
            val logInterceptor = HttpLoggingInterceptor()
            if (BuildConfig.DEBUG) {
                logInterceptor.level = HttpLoggingInterceptor.Level.BODY
            } else {
                logInterceptor.level = HttpLoggingInterceptor.Level.BASIC
            }

            builder.connectTimeout(DEFAULT_TIME_OUT.toLong(), TimeUnit.SECONDS)
                .readTimeout(DEFAULT_READ_TIME_OUT.toLong(), TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_WRITE_TIME_OUT.toLong(), TimeUnit.SECONDS)
                //错误重连
                .retryOnConnectionFailure(true)
                .addInterceptor(logInterceptor)

            handleBuilder(builder)
            return builder.build()

        }

    protected abstract fun handleBuilder(builder: OkHttpClient.Builder)

    fun <S> getService(serviceClass: Class<S>, baseUrl: String): S {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(serviceClass)
    }
}