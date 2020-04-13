package com.gerry.wanandroid.project.mvp

import com.gerry.wanandroid.http.BaseRetrofit
import com.gerry.wanandroid.http.bean.ArticleList
import com.gerry.wanandroid.http.bean.ResultData
import com.gerry.wanandroid.http.bean.TreeBean
import com.gerry.wanandroid.http.rx.ResponseTransformer
import io.reactivex.Observer

class ProjectModel {

    fun getProjectTree(observer: Observer<ResultData<List<TreeBean>>>) {
        val projectTree = BaseRetrofit.getRequest()?.getProjectTree()
        projectTree?.compose(ResponseTransformer.observeOnMainThread())?.subscribe(observer)
    }

    fun getProjectArticleByCid(page: Int, cid: Int, observer: Observer<ResultData<ArticleList>>) {
        val projectArticleList = BaseRetrofit.getRequest()?.getProjectListByCid(page, cid)
        projectArticleList?.compose(ResponseTransformer.observeOnMainThread())?.subscribe(observer)
    }
}