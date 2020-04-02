package com.gerry.wanandroid.wxofficial.mvp

import com.gerry.wanandroid.base.view.BaseView
import com.gerry.wanandroid.http.bean.ArticleList
import com.gerry.wanandroid.http.bean.NaviBean
import com.gerry.wanandroid.http.bean.TreeBean

interface IWxOfficialView : BaseView {
    fun getWxArticleChapterSuccess(data: List<TreeBean>)
    fun getWxArticleListSuccess(data: ArticleList)
}