package com.gerry.wanandroid.category.mvp

import com.gerry.wanandroid.http.BaseRetrofit
import com.gerry.wanandroid.http.bean.ArticleList
import com.gerry.wanandroid.http.bean.ResultData
import com.gerry.wanandroid.http.bean.TreeBean
import com.gerry.wanandroid.http.rx.ResponseTransformer
import io.reactivex.Observer

class SystemModel {
    fun getSystemTree(observer: Observer<ResultData<List<TreeBean>>>) {
        val systemTree = BaseRetrofit.getRequest()?.getTree()
        systemTree?.compose(ResponseTransformer.observeOnMainThread())?.subscribe(observer)
    }

    fun getArticleListByCid(page: Int, cid: Int, observer: Observer<ResultData<ArticleList>>) {
        val articleList = BaseRetrofit.getRequest()?.getTreeArticle(page, cid)
        articleList?.compose(ResponseTransformer.observeOnMainThread())?.subscribe(observer)
    }

    fun getArticleListByAuthor(
        page: Int,
        author: String,
        observer: Observer<ResultData<ArticleList>>
    ) {
        val articleListByAuthor = BaseRetrofit.getRequest()?.getArticleByAuthor(page, author)
        articleListByAuthor?.compose(ResponseTransformer.observeOnMainThread())?.subscribe(observer)
    }
}