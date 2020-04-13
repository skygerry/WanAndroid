package com.gerry.wanandroidmvvm.http

import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import com.gerry.wanandroid.utils.NetworkUtils
import com.gerry.wanandroidmvvm.WanApplication.Companion.CONTEXT
import com.gerry.wanandroidmvvm.base.http.BaseRetrofit
import okhttp3.*
import java.io.File


object WanAndroidBaseRetrofit : BaseRetrofit() {

    val service by lazy { getService(WanAndroidService::class.java, WanAndroidService.BASE_URL) }

    var cookieJar = PersistentCookieJar(SetCookieCache(), SharedPrefsCookiePersistor(CONTEXT))


    override fun handleBuilder(builder: OkHttpClient.Builder) {
        var httpCacheFile = File(CONTEXT.cacheDir, "wanresponse")
        val cacheSize = 10 * 1024 * 1024L
        val cache = Cache(httpCacheFile, cacheSize)
        builder.cache(cache)
            .cookieJar(cookieJar)
            .addInterceptor { chain ->
                var request = chain.request()
                if (!NetworkUtils.isAvailable(CONTEXT)) {
                    request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build()
                }
                val response = chain.proceed(request)
                if (NetworkUtils.isAvailable(CONTEXT)) {
                    val maxAge = 60 * 60 //有网络的时候设置的缓存超时时间
                    response.newBuilder()
                        .removeHeader("Pragma") //清除头信息
                        .header("Cache-Control", "public, max-age=$maxAge")
                        .build()
                } else {
                    val maxStale = 60 * 60 * 24 * 7 // 无网络的时候设置超时时间1周
                    response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
                        .build()
                }
                response
            }
    }


}