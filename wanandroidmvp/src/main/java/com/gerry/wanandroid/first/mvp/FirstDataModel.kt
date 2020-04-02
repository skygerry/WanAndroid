package com.gerry.wanandroid.first.mvp

import com.gerry.wanandroid.http.BaseRetrofit
import com.gerry.wanandroid.http.bean.ArticleBean
import com.gerry.wanandroid.http.bean.ArticleList
import com.gerry.wanandroid.http.bean.FirstBannerBean
import com.gerry.wanandroid.http.bean.ResultData
import com.gerry.wanandroid.http.rx.ResponseTransformer
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.functions.BiFunction


class FirstDataModel {

    fun getFirstArticleAndTop(page: Int,observer: Observer<ResultData<ArticleList>>) {
        val firstArticleAndTop = Observable.zip(BaseRetrofit.getRequest()?.getFirstArticleTop(), BaseRetrofit.getRequest()?.getFistArticleList(page)!!,
                BiFunction<ResultData<List<ArticleBean>>, ResultData<ArticleList>, ResultData<ArticleList>> { top, list ->
                    var articleList = ArticleList()
                    val firstArticleList = list.data
                    if (firstArticleList != null) {
                        articleList = firstArticleList
                    }
                    val topArticles = top.data
                    if (!topArticles.isNullOrEmpty()) {
                        articleList.datas.addAll(0, topArticles)
                    }

                    ResultData(articleList, list.errorCode, list.errorMsg)
                })
        firstArticleAndTop?.compose(ResponseTransformer.observeOnMainThread())?.subscribe(observer)
    }


    fun getFirstArticleList(page: Int, observer: Observer<ResultData<ArticleList>>) {
        val firstArticleList = BaseRetrofit.getRequest()?.getFistArticleList(page)
        firstArticleList?.compose(ResponseTransformer.observeOnMainThread())?.subscribe(observer)
    }

    fun getFirstArticleListByTime(page: Int, observer: Observer<ResultData<ArticleList>>) {
        val firstArticleByTimeList = BaseRetrofit.getRequest()?.getFirstArticleListByTime(page)
        firstArticleByTimeList?.compose(ResponseTransformer.observeOnMainThread())?.subscribe(observer)
    }

    fun getFirstBanner(observer: Observer<ResultData<List<FirstBannerBean>>>) {
        val firstBanner = BaseRetrofit.getRequest()?.getFirstBanner()
        firstBanner?.compose(ResponseTransformer.observeOnMainThread())?.subscribe(observer)
    }

    fun getFirstArticleTop(observer: Observer<ResultData<List<ArticleBean>>>) {
        val firstArticleTop = BaseRetrofit.getRequest()?.getFirstArticleTop()
        firstArticleTop?.compose(ResponseTransformer.observeOnMainThread())?.subscribe(observer)
    }
}