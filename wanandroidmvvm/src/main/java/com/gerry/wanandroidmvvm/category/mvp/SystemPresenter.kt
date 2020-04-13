package com.gerry.wanandroid.category.mvp

import com.gerry.wanandroidmvvm.base.presenter.BasePresenter
import com.gerry.wanandroid.http.bean.ArticleList
import com.gerry.wanandroid.http.bean.NaviBean
import com.gerry.wanandroid.http.bean.TreeBean
import com.gerry.wanandroid.http.rx.BaseObserver

class SystemPresenter : BasePresenter<ISystemView>() {
    private var systemModel: SystemModel = SystemModel()

    fun getSystemTree() {
        systemModel.getSystemTree(object : BaseObserver<List<TreeBean>>() {
            override fun onSuccess(data: List<TreeBean>) {
                view?.getSystemTreeSuccess(data)
            }

            override fun onFail(e: Throwable, errorMsg: String) {

            }
        })

    }

    fun getArticleByCid(page: Int, cid: Int) {
        systemModel.getArticleListByCid(page, cid, object : BaseObserver<ArticleList>() {
            override fun onSuccess(data: ArticleList) {
                view?.getArticleListSuccess(data)
            }

            override fun onFail(e: Throwable, errorMsg: String) {
            }
        })

    }

    fun getArticleByAuthor(page: Int, author: String) {
        systemModel.getArticleListByAuthor(page, author, object : BaseObserver<ArticleList>() {
            override fun onSuccess(data: ArticleList) {
                view?.getArticleListSuccess(data)
            }

            override fun onFail(e: Throwable, errorMsg: String) {
            }

        })
    }

    fun getNaviData() {
        systemModel.getNaviData(object : BaseObserver<List<NaviBean>>() {
            override fun onSuccess(data: List<NaviBean>) {
                view?.getNaviDataSuccess(data)
            }

            override fun onFail(e: Throwable, errorMsg: String) {

            }
        })
    }
}