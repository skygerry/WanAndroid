package com.gerry.wanandroid.first.mvp

import com.gerry.wanandroid.base.view.BaseView
import com.gerry.wanandroid.http.bean.ArticleList

interface IFirstView: BaseView {

    fun getFirstArticleListSuccess(articleList:ArticleList)
}