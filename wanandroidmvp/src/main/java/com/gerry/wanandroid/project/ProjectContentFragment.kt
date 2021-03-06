package com.gerry.wanandroid.project


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter

import com.gerry.wanandroid.R
import com.gerry.wanandroid.base.fragement.BaseFragment
import com.gerry.wanandroid.first.adapter.ProjectAdapter
import com.gerry.wanandroid.http.bean.ArticleBean
import com.gerry.wanandroid.http.bean.ArticleList
import com.gerry.wanandroid.http.bean.TreeBean
import com.gerry.wanandroid.project.mvp.IProjectView
import com.gerry.wanandroid.project.mvp.ProjectPresenter
import com.gerry.wanandroid.web.CommentWebActivity
import kotlinx.android.synthetic.main.fragment_new_project.*
import kotlinx.android.synthetic.main.fragment_project_content.*

private const val ARG_PARAM = "cid"

class ProjectContentFragment : BaseFragment<IProjectView, ProjectPresenter>(), IProjectView {
    private var cid: Int = 0

    var articleList = mutableListOf<ArticleBean>()
    lateinit var projectAdapter: ProjectAdapter

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

        projectAdapter = ProjectAdapter(R.layout.item_project)

        projectAdapter.onItemClickListener =
            BaseQuickAdapter.OnItemClickListener { adapter, view, position ->
                var intent = Intent(mContext, CommentWebActivity::class.java)
                intent.putExtra("url", articleList[position].link)
                startActivity(intent)
            }
        project_content_rv.layoutManager = LinearLayoutManager(mContext)
        project_content_rv.adapter = projectAdapter

        projectAdapter.setOnLoadMoreListener({
            if (canLoadMore) {
                getPresenter()?.getProjectArticle(currentPage,cid)
            }
        }, project_content_rv)

        project_content_refresh.setOnRefreshListener {
            currentPage = 1
            canLoadMore = true
            getPresenter()?.getProjectArticle(currentPage,cid)
        }


        getPresenter()?.getProjectArticle(currentPage,cid)

    }


    override fun getArticleListSuccess(data: ArticleList) {
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


    override fun getProjectTreeSuccess(data: List<TreeBean>) {}

    override fun init() {}
    override fun showLoadingDialog(msg: String) {}
    override fun dismissLoadingDialog() {}
    override fun onResponseError(msg: String?) {}

    override fun createView(): IProjectView = this
    override fun createPresenter(): ProjectPresenter = ProjectPresenter()
    override fun getLayoutId(): Int = R.layout.fragment_project_content

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
