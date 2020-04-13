package com.gerry.wanandroid.wxofficial.mvp

import com.gerry.wanandroidmvvm.base.view.BaseView
import com.gerry.wanandroid.http.bean.ArticleList
import com.gerry.wanandroid.http.bean.TreeBean

interface IWxOfficialView : BaseView {
    fun getWxArticleChapterSuccess(data: List<TreeBean>)
    fun getWxArticleListSuccess(data: ArticleList)
}