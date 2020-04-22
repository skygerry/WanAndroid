package com.gerry.basemvvm.network

import android.net.ParseException
import com.gerry.wanandroidmvvm.basemvvm.network.ERROR
import com.google.gson.JsonParseException
import com.google.gson.stream.MalformedJsonException
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLException

object ExceptionUtil {
    fun catchException(throwable: Throwable): ApiException {
        val ex: ApiException
        if (throwable is HttpException) {
            ex = ApiException(ERROR.HTTP_ERROR, throwable)
        } else if (throwable is JsonParseException
            || throwable is JSONException
            || throwable is ParseException
            || throwable is MalformedJsonException
        ) {
            ex = ApiException(ERROR.PARSE_ERROR, throwable)
        } else if (throwable is ConnectException) {
            ex = ApiException(ERROR.NETWORD_ERROR, throwable)
        } else if (throwable is SSLException) {
            ex = ApiException(ERROR.SSL_ERROR, throwable)
        } else if (throwable is SocketTimeoutException
            || throwable is UnknownHostException
        ) {
            ex = ApiException(ERROR.TIMEOUT_ERROR, throwable)
        } else {
            ex = if (!throwable.message.isNullOrEmpty()) {
                ApiException(1000, throwable.message!!, throwable)
            } else {
                ApiException(ERROR.UNKNOWN, throwable)
            }
        }
        return ex
    }
}
