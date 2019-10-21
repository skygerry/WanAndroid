package com.gerry.wanandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.gerry.wanandroid.http.rx.BaseObserver
import com.gerry.wanandroid.http.BaseRetrofit
import com.gerry.wanandroid.http.rx.ResponseTransformer
import com.gerry.wanandroid.http.bean.TreeBean

class MainActivity : AppCompatActivity() {
    val baseUrl = "https://wanandroid.com/";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        BaseRetrofit.getInstance()?.init()
        BaseRetrofit.getRequest()?.getWxArticleChapters()
                ?.compose(ResponseTransformer.observeOnMainThread())
                ?.subscribe(object : BaseObserver<List<TreeBean>>(this) {
                    override fun onSuccess(data: List<TreeBean>) {
                        if (data != null) {
                            for (i in data) {
                                Log.e("xyh", "onResponse: " + i.name)
                            }
                        }
                    }

                    override fun onFail(e: Throwable, errorMsg: String) {
                        Log.e("xyh", "onFailure: $errorMsg")
                    }

                })
    }
}
