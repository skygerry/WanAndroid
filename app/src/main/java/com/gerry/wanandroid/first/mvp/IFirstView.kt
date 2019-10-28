package com.gerry.wanandroid.first.mvp

import com.gerry.wanandroid.base.view.BaseView
import com.gerry.wanandroid.http.bean.ArticleBean
import com.gerry.wanandroid.http.bean.ArticleList
import com.gerry.wanandroid.http.bean.FirstBannerBean

interface IFirstView : BaseView {

    fun getFirstArticleListSuccess(articleList: ArticleList)

    fun getFirstArticleByTimeListSuccess(data: ArticleList)

    fun getFirstBannerSuccess(data: List<FirstBannerBean>)

    fun getFirstArticleTopSuccess(data: List<ArticleBean>)
}