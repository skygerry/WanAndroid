package com.gerry.wanandroid.wxofficial


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter

import com.gerry.wanandroid.R
import com.gerry.wanandroidmvvm.base.fragement.BaseFragment
import com.gerry.wanandroidmvvm.category.adapter.SystemArticleAdapter
import com.gerry.wanandroid.http.bean.ArticleBean
import com.gerry.wanandroid.http.bean.ArticleList
import com.gerry.wanandroid.http.bean.TreeBean
import com.gerry.wanandroid.web.CommentWebActivity
import com.gerry.wanandroid.wxofficial.mvp.IWxOfficialView
import com.gerry.wanandroid.wxofficial.mvp.WxOfficialPresenter
import kotlinx.android.synthetic.main.fragment_wx_official_content.*

private const val ARG_PARAM = "id"

class WxOfficialContentFragment : BaseFragment<IWxOfficialView, WxOfficialPresenter>(),
    IWxOfficialView {

    private var cid: Int = 0

    var articleList = mutableListOf<ArticleBean>()
    lateinit var systemArticleAdapter: SystemArticleAdapter

    var currentPage = 1

    var canLoadMore = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            cid = it.getInt(ARG_PARAM)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        systemArticleAdapter = SystemArticleAdapter(R.layout.item_article_system)

        systemArticleAdapter.onItemClickListener =
            BaseQuickAdapter.OnItemClickListener { adapter, view, position ->
                var intent = Intent(mContext, CommentWebActivity::class.java)
                intent.putExtra("url", articleList[position].link)
                startActivity(intent)
            }
        wx_official_content_rv.layoutManager = LinearLayoutManager(mContext)
        wx_official_content_rv.adapter = systemArticleAdapter

        systemArticleAdapter.setOnLoadMoreListener({
            if (canLoadMore) {
                getPresenter()?.getWxArticleList(cid, currentPage)
            }
        }, wx_official_content_rv)

        wx_official_content_refresh.setOnRefreshListener {
            currentPage = 1
            canLoadMore = true
            getPresenter()?.getWxArticleList(cid, currentPage)
        }


        getPresenter()?.getWxArticleList(cid, currentPage)

    }

    override fun getWxArticleListSuccess(data: ArticleList) {
        if (data != null) {
            if (currentPage == 1) {
                articleList.clear()
            }
            articleList.addAll(data.datas)
            systemArticleAdapter.setNewData(this.articleList)
            if (data.curPage < data.pageCount) {
                currentPage++
                canLoadMore = true
                systemArticleAdapter.loadMoreComplete()
            } else {
                canLoadMore = false
                systemArticleAdapter.loadMoreEnd()
            }
            wx_official_content_refresh.isRefreshing = false

        }
        Log.e("----->", data.datas.size.toString())
    }

    override fun getWxArticleChapterSuccess(data: List<TreeBean>) {}

    override fun init() {}
    override fun showLoadingDialog(msg: String) {}
    override fun dismissLoadingDialog() {}
    override fun onResponseError(msg: String?) {}

    override fun createView(): IWxOfficialView = this
    override fun createPresenter(): WxOfficialPresenter = WxOfficialPresenter()
    override fun getLayoutId(): Int = R.layout.fragment_wx_official_content

    companion object {
        @JvmStatic
        fun newInstance(cid: Int) =
            WxOfficialContentFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM, cid)
                }
            }
    }
}