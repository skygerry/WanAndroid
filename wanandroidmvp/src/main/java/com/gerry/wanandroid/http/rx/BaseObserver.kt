package com.gerry.wanandroid.http.rx

import com.gerry.wanandroid.http.bean.ResultData
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import org.json.JSONException
import com.google.gson.JsonParseException
import android.net.ParseException
import android.util.Log
import com.gerry.wanandroid.http.exception.ApiException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException


abstract class BaseObserver<T> : Observer<ResultData<T>> {
    private var mDisposable: Disposable? = null
    private val SUCCESS_CODE = 0


    override fun onSubscribe(d: Disposable) {
        mDisposable = d
    }

    override fun onNext(t: ResultData<T>) {
        if (t.errorCode == SUCCESS_CODE) {
            var t: T = t.data
            onSuccess(t)
        } else {
            var exception = ApiException(t.errorCode, t.errorMsg)
            onError(exception)
        }

    }

    override fun onError(e: Throwable) {
        if (e is JsonParseException
            || e is JSONException
            || e is ParseException
        ) {
            //解析错误
            e.message?.let { onFail(e, it) }
        } else if (e is ConnectException) {
            //网络错误
            e.message?.let { onFail(e, it) }
        } else if (e is UnknownHostException || e is SocketTimeoutException) {
            //连接错误
            e.message?.let { onFail(e, it) }
        } else if (e is ApiException) {
            //未知错误
            if (e.code == -1001) {
                onFail(e, "账号未登录")
            } else {
                e.displayMessage?.let { onFail(e, it) }
            }
        } else {
            onFail(e, "未知错误")
        }
        mDisposable?.dispose()

    }

    override fun onComplete() {
        Log.e("----->", "成功了")
        mDisposable?.dispose()
    }

    protected abstract fun onSuccess(data: T)

    protected abstract fun onFail(e: Throwable, errorMsg: String)

}