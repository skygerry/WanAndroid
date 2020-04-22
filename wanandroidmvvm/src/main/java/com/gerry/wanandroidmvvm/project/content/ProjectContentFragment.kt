package com.gerry.wanandroidmvvm.project.content


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.gerry.basemvvm.base.BaseFragment

import com.gerry.wanandroidmvvm.first.adapter.ProjectAdapter
import com.gerry.wanandroid.http.bean.ArticleBean
import com.gerry.wanandroid.http.bean.ArticleList
import com.gerry.wanandroidmvvm.R
import com.gerry.wanandroidmvvm.category.netnav.NetNavViewModel
import com.gerry.wanandroidmvvm.web.CommentWebActivity
import kotlinx.android.synthetic.main.fragment_project_content.*

private const val ARG_PARAM = "cid"

class ProjectContentFragment : BaseFragment<ProjectContentViewModel>() {
    private var cid: Int = 0

    var articleList = mutableListOf<ArticleBean>()
    private val projectAdapter by lazy { ProjectAdapter() }

    var currentPage = 1

    var canLoadMore = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            cid = it.getInt(ARG_PARAM)
        }
    }

    override fun initView(savedInstanceState: Bundle?) {
        projectAdapter.onItemClickListener =
            BaseQuickAdapter.OnItemClickListener { adapter, view, position ->
                var intent = Intent(context, CommentWebActivity::class.java)
                intent.putExtra("url", articleList[position].link)
                startActivity(intent)
            }
        project_content_rv.layoutManager = LinearLayoutManager(context)
        project_content_rv.adapter = projectAdapter

        projectAdapter.setOnLoadMoreListener({
            if (canLoadMore) {
                viewModel.getProjectArticle(currentPage, cid)
            }
        }, project_content_rv)

        project_content_refresh.setOnRefreshListener {
            currentPage = 1
            canLoadMore = true
            viewModel.getProjectArticle(currentPage, cid)
        }

    }

    override fun lazyLoadData() {
        viewModel.run {
            getProjectArticle(currentPage, cid).observe(this@ProjectContentFragment, Observer {
                getArticleListSuccess(it)
            })
        }
    }

    private fun getArticleListSuccess(data: ArticleList) {
        if (data != null) {
            if (currentPage == 1) {
                articleList.clear()
            }
            articleList.addAll(data.datas)
            projectAdapter.setNewData(this.articleList)
            if (data.curPage < data.pageCount) {
                currentPage++
                canLoadMore = true
                projectAdapter.loadMoreComplete()
            } else {
                canLoadMore = false
                projectAdapter.loadMoreEnd()
            }
            project_content_refresh.isRefreshing = false

        }
        Log.e("----->", data.datas.size.toString())
    }

    override fun getLayoutId(): Int = R.layout.fragment_project_content

    override fun createViewModel(): ProjectContentViewModel {
        return ViewModelProvider(this).get(ProjectContentViewModel::class.java)
    }

    companion object {
        @JvmStatic
        fun newInstance(cid: Int) =
            ProjectContentFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM, cid)
                }
            }
    }
}
