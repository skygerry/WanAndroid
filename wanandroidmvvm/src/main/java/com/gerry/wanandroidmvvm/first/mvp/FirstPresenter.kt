package com.gerry.wanandroid.first.mvp

import com.gerry.wanandroidmvvm.base.presenter.BasePresenter
import com.gerry.wanandroid.http.BaseRetrofit
import com.gerry.wanandroid.http.bean.ArticleBean
import com.gerry.wanandroid.http.bean.ArticleList
import com.gerry.wanandroid.http.bean.FirstBannerBean
import com.gerry.wanandroid.http.rx.BaseObserver

class FirstPresenter : BasePresenter<IFirstView>() {
    private var firstDataModel: FirstDataModel = FirstDataModel()


    fun getFirstArticleTopAndList(page: Int) {
        firstDataModel.getFirstArticleAndTop(page, object : BaseObserver<ArticleList>() {
            override fun onSuccess(data: ArticleList) {
                view?.getFirstArticleListSuccess(data)
            }

            override fun onFail(e: Throwable, errorMsg: String) {
            }

        })


    }


    fun getFirstArticleList(page: Int) {
        firstDataModel.getFirstArticleList(page, object : BaseObserver<ArticleList>() {
            override fun onSuccess(data: ArticleList) {
                view?.getFirstArticleListSuccess(data)
            }

            override fun onFail(e: Throwable, errorMsg: String) {
            }

        })
    }

    fun getFirstArticleByTimeList(page: Int) {
        firstDataModel.getFirstArticleListByTime(page, object : BaseObserver<ArticleList>() {
            override fun onSuccess(data: ArticleList) {
                view?.getFirstArticleByTimeListSuccess(data)
            }

            override fun onFail(e: Throwable, errorMsg: String) {
            }

        })
    }

    fun getFirstBanner() {
        firstDataModel.getFirstBanner(object : BaseObserver<List<FirstBannerBean>>() {
            override fun onSuccess(data: List<FirstBannerBean>) {
                view?.getFirstBannerSuccess(data)
            }

            override fun onFail(e: Throwable, errorMsg: String) {
            }

        })
    }

    fun getFirstArticleTop() {
        firstDataModel.getFirstArticleTop(object : BaseObserver<List<ArticleBean>>() {
            override fun onSuccess(data: List<ArticleBean>) {
                view?.getFirstArticleTopSuccess(data)
            }

            override fun onFail(e: Throwable, errorMsg: String) {
            }

        })
    }
}