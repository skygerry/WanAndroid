package com.gerry.wanandroidmvvm.wxofficial.content


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.gerry.basemvvm.base.BaseFragment

import com.gerry.wanandroidmvvm.category.adapter.SystemArticleAdapter
import com.gerry.wanandroid.http.bean.ArticleBean
import com.gerry.wanandroid.http.bean.ArticleList
import com.gerry.wanandroidmvvm.web.CommentWebActivity
import com.gerry.wanandroidmvvm.R
import kotlinx.android.synthetic.main.fragment_wx_official_content.*

private const val ARG_PARAM = "id"

class WxOfficialContentFragment : BaseFragment<WxOfficialContentViewModel>() {

    private var cid: Int = 0

    var articleList = mutableListOf<ArticleBean>()
    private val systemArticleAdapter by lazy { SystemArticleAdapter() }

    var currentPage = 1

    var canLoadMore = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            cid = it.getInt(ARG_PARAM)
        }
    }

    override fun initView(savedInstanceState: Bundle?) {
        systemArticleAdapter.onItemClickListener =
            BaseQuickAdapter.OnItemClickListener { adapter, _, position ->
                val article = adapter.data[position] as ArticleBean
                var intent = Intent(context, CommentWebActivity::class.java)
                intent.putExtra("url", article.link)
                startActivity(intent)
            }
        wx_official_content_rv.layoutManager = LinearLayoutManager(context)
        wx_official_content_rv.adapter = systemArticleAdapter

        systemArticleAdapter.setOnLoadMoreListener({
            if (canLoadMore) {
                viewModel.getWxArticleList(cid, currentPage)
            }
        }, wx_official_content_rv)

        wx_official_content_refresh.setOnRefreshListener {
            currentPage = 1
            canLoadMore = true
            viewModel.getWxArticleList(cid, currentPage)
        }
    }

    override fun lazyLoadData() {
        viewModel.run {
            getWxArticleList(cid, currentPage).observe(this@WxOfficialContentFragment, Observer {
                getWxArticleListSuccess(it)
            })
        }
    }

    private fun getWxArticleListSuccess(data: ArticleList) {
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