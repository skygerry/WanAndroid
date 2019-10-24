package com.gerry.wanandroid.first.mvp

import com.gerry.wanandroid.base.BasePresenter
import com.gerry.wanandroid.http.bean.ArticleList
import com.gerry.wanandroid.http.rx.BaseObserver

class FirstPresenter : BasePresenter<IFirstView>() {
    var firstDataModel: FirstDataModel = FirstDataModel()

    fun getFirstArticleList(page: Int) {
        firstDataModel.getFirstArticleList(page, object : BaseObserver<ArticleList>() {
            override fun onSuccess(data: ArticleList) {
                view?.getFirstArticleListSuccess(data)
            }

            override fun onFail(e: Throwable, errorMsg: String) {
            }

        })
    }
}