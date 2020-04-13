package com.gerry.wanandroid.project.mvp

import com.gerry.wanandroidmvvm.base.view.BaseView
import com.gerry.wanandroid.http.bean.ArticleList
import com.gerry.wanandroid.http.bean.TreeBean

interface IProjectView: BaseView {
    fun getProjectTreeSuccess(data: List<TreeBean>)
    fun getArticleListSuccess(data: ArticleList)
}