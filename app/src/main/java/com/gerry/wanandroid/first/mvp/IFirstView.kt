package com.gerry.wanandroid.first.mvp

import com.gerry.wanandroid.base.BaseView
import com.gerry.wanandroid.http.bean.ArticleList

interface IFirstView:BaseView {

    fun getFirstArticleListSuccess(articleList:ArticleList)
}