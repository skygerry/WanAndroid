package com.gerry.wanandroid.category


import android.util.Log

import com.gerry.wanandroid.R
import com.gerry.wanandroid.base.fragement.BaseFragment
import com.gerry.wanandroid.category.mvp.ISystemView
import com.gerry.wanandroid.category.mvp.SystemPresenter
import com.gerry.wanandroid.first.mvp.FirstPresenter
import com.gerry.wanandroid.first.mvp.IFirstView
import com.gerry.wanandroid.http.bean.ArticleBean
import com.gerry.wanandroid.http.bean.ArticleList
import com.gerry.wanandroid.http.bean.FirstBannerBean
import com.gerry.wanandroid.http.bean.TreeBean

class SystemFragment : BaseFragment<ISystemView, SystemPresenter>(), ISystemView {


    override fun getSystemTreeSuccess(data: List<TreeBean>) {

    }

    override fun getArticleListSuccess(data: ArticleList) {
    }


    override fun showLoadingDialog(msg: String) {
    }

    override fun dismissLoadingDialog() {
    }

    override fun onResponseError(msg: String?) {
    }

    override fun createView(): ISystemView = this

    override fun createPresenter(): SystemPresenter = SystemPresenter()

    override fun getLayoutId(): Int = R.layout.fragment_system

    override fun init() {
    }
}