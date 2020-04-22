package com.gerry.wanandroidmvvm.first.newproject


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.gerry.basemvvm.base.BaseFragment
import com.gerry.wanandroidmvvm.first.adapter.ProjectAdapter
import com.gerry.wanandroid.http.bean.ArticleBean
import com.gerry.wanandroid.http.bean.ArticleList
import com.gerry.wanandroidmvvm.R
import com.gerry.wanandroidmvvm.web.CommentWebActivity
import kotlinx.android.synthetic.main.fragment_new_project.*

class NewProjectFragment : BaseFragment<NewProjectViewModel>() {
    private var articleList = mutableListOf<ArticleBean>()
    private val projectAdapter by lazy { ProjectAdapter() }

    private var currentPage = 0

    private var canLoadMore = true

    override fun initView(savedInstanceState: Bundle?) {
        with(first_project_rv) {
            layoutManager = LinearLayoutManager(context)
            adapter = projectAdapter
        }

        projectAdapter.apply {
            setOnItemClickListener { adapter, _, position ->
                val article = adapter.data[position] as ArticleBean
                var intent = Intent(context, CommentWebActivity::class.java)
                intent.putExtra("url", article.link)
                startActivity(intent)
            }

            setOnLoadMoreListener({
                if (canLoadMore) {
                    viewModel.getFirstArticleByTimeList(currentPage)
                }
            }, first_project_rv)
        }

        first_project_refresh.setOnRefreshListener {
            currentPage = 0
            canLoadMore = true
            viewModel.getFirstArticleByTimeList(currentPage)
        }


    }

    override fun lazyLoadData() {
        viewModel.run {
            getFirstArticleByTimeList(currentPage).observe(this@NewProjectFragment, Observer {
                getFirstArticleByTimeListSuccess(it)
            })

        }
    }

    fun getFirstArticleByTimeListSuccess(data: ArticleList) {
        if (data != null) {
            if (currentPage == 0) {
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
            first_project_refresh.isRefreshing = false

        }
        Log.e("----->", data.datas.size.toString())
    }

    override fun getLayoutId(): Int = R.layout.fragment_new_project
}
