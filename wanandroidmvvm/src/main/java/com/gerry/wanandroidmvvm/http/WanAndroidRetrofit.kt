package com.gerry.wanandroidmvvm.http

import com.blankj.utilcode.util.NetworkUtils
import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import com.gerry.wanandroidmvvm.http.bean.ResultData
import com.gerry.wanandroidmvvm.WanApplication.Companion.CONTEXT
import com.gerry.basemvvm.network.ApiException
import com.gerry.basemvvm.network.BaseRetrofit
import okhttp3.*
import java.io.File


object WanAndroidRetrofit : BaseRetrofit() {

    val service by lazy { getService(WanAndroidService::class.java, WanAndroidService.BASE_URL) }

    private var cookieJar = PersistentCookieJar(SetCookieCache(), SharedPrefsCookiePersistor(CONTEXT))


    override fun handleBuilder(builder: OkHttpClient.Builder) {
        var httpCacheFile = File(CONTEXT.cacheDir, "wanresponse")
        val cacheSize = 10 * 1024 * 1024L
        val cache = Cache(httpCacheFile, cacheSize)
        builder.cache(cache)
            .cookieJar(cookieJar)
            .addInterceptor { chain ->
                var request = chain.request()
                if (!NetworkUtils.isAvailable()) {
                    request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build()
                }
                val response = chain.proceed(request)
                if (NetworkUtils.isAvailable()) {
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

    suspend fun <T: ResultData<T>> preProcessingData(resultData:T): T =
        if (resultData.errorCode == 0) {// 成功
            // 返回数据
            resultData.data
        } else {// 失败
            // 验证登录是否过期
//        validateCode(context, baseBean.code)
            // 抛出接口异常 ApiException是自定义的异常类
            throw ApiException(
                resultData.errorCode,
                resultData.errorMsg
            )
        }

}