package com.gerry.wanandroid.wxofficial.mvp

import com.gerry.wanandroid.http.BaseRetrofit
import com.gerry.wanandroid.http.bean.ArticleList
import com.gerry.wanandroid.http.bean.ResultData
import com.gerry.wanandroid.http.bean.TreeBean
import com.gerry.wanandroid.http.rx.ResponseTransformer
import io.reactivex.Observer

class WxOfficialModel {

    fun getWxArticleChapters(observer: Observer<ResultData<List<TreeBean>>>) {
        val wxArticleChapters = BaseRetrofit.getRequest()?.getWxArticleChapters()
        wxArticleChapters?.compose(ResponseTransformer.observeOnMainThread())?.subscribe(observer)
    }

    fun getWxArticleList(id: Int, page: Int, observer: Observer<ResultData<ArticleList>>) {
        val wxArticleList = BaseRetrofit.getRequest()?.getWxArticleList(id, page)
        wxArticleList?.compose(ResponseTransformer.observeOnMainThread())?.subscribe(observer)
    }
}