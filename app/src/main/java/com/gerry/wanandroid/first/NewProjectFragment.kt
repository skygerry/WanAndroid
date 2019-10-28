package com.gerry.wanandroid.first


import com.gerry.wanandroid.R
import com.gerry.wanandroid.base.fragement.BaseFragment
import com.gerry.wanandroid.first.mvp.FirstPresenter
import com.gerry.wanandroid.first.mvp.IFirstView
import com.gerry.wanandroid.http.bean.ArticleBean
import com.gerry.wanandroid.http.bean.ArticleList
import com.gerry.wanandroid.http.bean.FirstBannerBean

class NewProjectFragment : BaseFragment<IFirstView, FirstPresenter>(), IFirstView {
    override fun getFirstArticleTopSuccess(data: List<ArticleBean>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun init() {
    }


    override fun getFirstArticleListSuccess(articleList: ArticleList) {
    }

    override fun getFirstArticleByTimeListSuccess(data: ArticleList) {
    }

    override fun getFirstBannerSuccess(data: List<FirstBannerBean>) {

    }

    override fun showLoadingDialog(msg: String) {
    }

    override fun dismissLoadingDialog() {
    }

    override fun onResponseError(msg: String?) {
    }

    override fun createView(): IFirstView = this

    override fun createPresenter(): FirstPresenter = FirstPresenter()

    override fun getLayoutId(): Int = R.layout.fragment_new_project

}
