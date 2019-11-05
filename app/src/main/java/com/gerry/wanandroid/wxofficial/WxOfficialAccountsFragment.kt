package com.gerry.wanandroid.wxofficial

import com.gerry.wanandroid.R
import com.gerry.wanandroid.base.fragement.BaseFragment
import com.gerry.wanandroid.http.bean.ArticleList
import com.gerry.wanandroid.http.bean.NaviBean
import com.gerry.wanandroid.http.bean.TreeBean
import com.gerry.wanandroid.wxofficial.mvp.INavView
import com.gerry.wanandroid.wxofficial.mvp.NavPresenter

class WxOfficialAccountsFragment : BaseFragment<INavView, NavPresenter>(), INavView {


    override fun getNaviDataSuccess(data: List<NaviBean>) {
    }

    override fun getWxArticleChapterSuccess(data: List<TreeBean>) {
    }

    override fun getWxArticleListSuccess(data: ArticleList) {
    }

    override fun showLoadingDialog(msg: String) {}
    override fun dismissLoadingDialog() {}
    override fun onResponseError(msg: String?) {}

    override fun init() {}

    override fun createView(): INavView = this
    override fun createPresenter(): NavPresenter = NavPresenter()
    override fun getLayoutId(): Int = R.layout.fragment_wx_official_accounts

}
