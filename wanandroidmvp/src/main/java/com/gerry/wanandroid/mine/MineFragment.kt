package com.gerry.wanandroid.mine


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.gerry.wanandroid.R
import com.gerry.wanandroid.base.fragement.BaseFragment
import com.gerry.wanandroid.first.mvp.FirstPresenter
import com.gerry.wanandroid.first.mvp.IFirstView
import com.gerry.wanandroid.http.bean.ArticleBean
import com.gerry.wanandroid.http.bean.ArticleList
import com.gerry.wanandroid.http.bean.FirstBannerBean

class MineFragment : BaseFragment<IFirstView, FirstPresenter>(), IFirstView {
    override fun getFirstArticleTopSuccess(data: List<ArticleBean>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getFirstBannerSuccess(data: List<FirstBannerBean>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun init() {
        getPresenter()?.getFirstArticleList(0)
    }

    override fun createView(): IFirstView {
        return this
    }

    override fun createPresenter(): FirstPresenter {
        return FirstPresenter()
    }

    override fun getLayoutId(): Int = R.layout.fragment_mine

    override fun getFirstArticleListSuccess(articleList: ArticleList) {
        if (articleList != null) {
            for (i in articleList.datas) {
                Log.e("xyh", "onResponse: " + i.title)
            }
        }
        Log.e("----->", articleList.datas.size.toString())
    }
    override fun getFirstArticleByTimeListSuccess(articleList: ArticleList) {
        if (articleList != null) {
            for (i in articleList.datas) {
                Log.e("xyh", "onResponse: " + i.title)
            }
        }
        Log.e("----->", articleList.datas.size.toString())
    }
    override fun showLoadingDialog(msg: String) {
    }

    override fun dismissLoadingDialog() {
    }

    override fun onResponseError(msg: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}