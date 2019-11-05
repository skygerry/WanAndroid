package com.gerry.wanandroid.wxofficial.mvp

import com.gerry.wanandroid.base.BasePresenter
import com.gerry.wanandroid.http.bean.ArticleList
import com.gerry.wanandroid.http.bean.TreeBean
import com.gerry.wanandroid.http.rx.BaseObserver

class NavPresenter : BasePresenter<INavView>() {
    var navModel: NavModel = NavModel()



    fun getWxArticleChapters() {
        navModel.getWxArticleChapters(object : BaseObserver<List<TreeBean>>() {
            override fun onSuccess(data: List<TreeBean>) {
                view?.getWxArticleChapterSuccess(data)
            }

            override fun onFail(e: Throwable, errorMsg: String) {
            }
        })
    }

    fun getWxArticleList(cid:Int,page:Int){
        navModel.getWxArticleList(cid,page,object:BaseObserver<ArticleList>(){
            override fun onSuccess(data: ArticleList) {
                view?.getWxArticleListSuccess(data)
            }

            override fun onFail(e: Throwable, errorMsg: String) {
            }

        })
    }

}