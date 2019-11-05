package com.gerry.wanandroid.wxofficial

import com.gerry.wanandroid.R
import com.gerry.wanandroid.base.fragement.BaseFragment
import com.gerry.wanandroid.http.bean.ArticleList
import com.gerry.wanandroid.http.bean.NaviBean
import com.gerry.wanandroid.http.bean.TreeBean
import com.gerry.wanandroid.wxofficial.mvp.IWxOfficialView
import com.gerry.wanandroid.wxofficial.mvp.WxOfficialPresenter

/**
 * 公众号
 */
class WxOfficialAccountsFragment : BaseFragment<IWxOfficialView, WxOfficialPresenter>(), IWxOfficialView {


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

    override fun createView(): IWxOfficialView = this
    override fun createPresenter(): WxOfficialPresenter = WxOfficialPresenter()
    override fun getLayoutId(): Int = R.layout.fragment_wx_official_accounts

}
