package com.gerry.wanandroidmvvm.category.systemarticle

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.gerry.basemvvm.base.BaseActivity
import com.gerry.wanandroidmvvm.category.adapter.SystemArticleAdapter
import com.gerry.wanandroid.http.bean.ArticleBean
import com.gerry.wanandroid.http.bean.ArticleList
import com.gerry.wanandroidmvvm.R
import com.gerry.wanandroidmvvm.web.CommentWebActivity
import kotlinx.android.synthetic.main.activity_system_article.*

class SystemArticleActivity : BaseActivity<SystemArticleViewModel>() {

    var articleList = mutableListOf<ArticleBean>()
    private val systemArticleAdapter by lazy { SystemArticleAdapter() }

    var currentPage = 0

    var cid = 0

    var title = ""

    var canLoadMore = true

    override fun initView(savedInstanceState: Bundle?) {
        system_title_tv.text = title

        with(system_article_rv) {
            layoutManager = LinearLayoutManager(this@SystemArticleActivity)
            adapter = systemArticleAdapter
        }

        systemArticleAdapter.apply {
            setOnItemChildClickListener { adapter, _, position ->
                val articleBean = adapter.data[position] as ArticleBean
                var intent = Intent(this@SystemArticleActivity, CommentWebActivity::class.java)
                intent.putExtra("url", articleBean.link)
                startActivity(intent)
            }

            setOnLoadMoreListener({
                if (canLoadMore) {
                    viewModel.getArticleByCid(currentPage, cid)
                }
            }, system_article_rv)
        }

        system_back_iv.setOnClickListener {
            finish()
        }
    }

    override fun initData() {
        if (intent != null) {
            cid = intent.getIntExtra("cid", 0)
            if (intent.getStringExtra("title") != null) {
                title = intent.getStringExtra("title")
            }
        }
        viewModel.run {
            getArticleByCid(currentPage, cid).observe(this@SystemArticleActivity, Observer {
                getArticleListSuccess(it)
            })
        }
    }

    fun getArticleListSuccess(data: ArticleList) {
        if (data != null) {
            if (currentPage == 0) {
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

        }
        Log.e("----->", data.datas.size.toString())
    }

    override fun getLayoutId(): Int = R.layout.activity_system_article
}
