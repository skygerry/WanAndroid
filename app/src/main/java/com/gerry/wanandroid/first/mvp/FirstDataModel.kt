package com.gerry.wanandroid.first.mvp

import com.gerry.wanandroid.http.BaseRetrofit
import com.gerry.wanandroid.http.bean.ArticleList
import com.gerry.wanandroid.http.bean.ResultData
import com.gerry.wanandroid.http.rx.ResponseTransformer
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class FirstDataModel {
    fun getFirstArticleList(page: Int, observer: Observer<ResultData<ArticleList>>) {
        val firstArticleList = BaseRetrofit.getRequest()?.getFistArticleList(page)
        firstArticleList?.compose(ResponseTransformer.observeOnMainThread())?.subscribe(observer)
    }

}