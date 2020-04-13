package com.gerry.wanandroid.category.mvp

import com.gerry.wanandroidmvvm.base.view.BaseView
import com.gerry.wanandroid.http.bean.ArticleList
import com.gerry.wanandroid.http.bean.NaviBean
import com.gerry.wanandroid.http.bean.TreeBean

interface ISystemView : BaseView {
    fun getSystemTreeSuccess(data: List<TreeBean>)

    fun getArticleListSuccess(data: ArticleList)

    fun getNaviDataSuccess(data: List<NaviBean>)
}