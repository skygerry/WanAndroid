package com.gerry.wanandroid.project.mvp

import com.gerry.wanandroidmvvm.base.presenter.BasePresenter
import com.gerry.wanandroid.http.bean.ArticleList
import com.gerry.wanandroid.http.bean.TreeBean
import com.gerry.wanandroid.http.rx.BaseObserver

class ProjectPresenter : BasePresenter<IProjectView>() {
    var projectModel: ProjectModel = ProjectModel()

    fun getProjectTree(){
        projectModel.getProjectTree(object : BaseObserver<List<TreeBean>>() {
            override fun onSuccess(data: List<TreeBean>) {
                view?.getProjectTreeSuccess(data)
            }

            override fun onFail(e: Throwable, errorMsg: String) {

            }
        })
    }

    fun getProjectArticle(page:Int, cid:Int){
        projectModel.getProjectArticleByCid(page, cid, object : BaseObserver<ArticleList>() {
            override fun onSuccess(data: ArticleList) {
                view?.getArticleListSuccess(data)
            }

            override fun onFail(e: Throwable, errorMsg: String) {
            }
        })
    }


}