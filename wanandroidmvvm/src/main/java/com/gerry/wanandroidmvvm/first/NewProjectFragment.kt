package com.gerry.wanandroid.first


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.gerry.wanandroid.R
import com.gerry.wanandroidmvvm.base.fragement.BaseFragment
import com.gerry.wanandroid.first.adapter.ProjectAdapter
import com.gerry.wanandroid.first.mvp.FirstPresenter
import com.gerry.wanandroid.first.mvp.IFirstView
import com.gerry.wanandroid.http.bean.ArticleBean
import com.gerry.wanandroid.http.bean.ArticleList
import com.gerry.wanandroid.http.bean.FirstBannerBean
import com.gerry.wanandroid.web.CommentWebActivity
import kotlinx.android.synthetic.main.fragment_new_project.*

class NewProjectFragment : BaseFragment<IFirstView, FirstPresenter>(), IFirstView {
    var articleList = mutableListOf<ArticleBean>()
    lateinit var projectAdapter: ProjectAdapter

    var currentPage = 0

    var canLoadMore = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        projectAdapter = ProjectAdapter(R.layout.item_project)

        projectAdapter.onItemClickListener =
            BaseQuickAdapter.OnItemClickListener { adapter, view, position ->
                var intent = Intent(mContext, CommentWebActivity::class.java)
                intent.putExtra("url", articleList[position].link)
                startActivity(intent)
            }
        first_project_rv.layoutManager = LinearLayoutManager(mContext)
        first_project_rv.adapter = projectAdapter

        projectAdapter.setOnLoadMoreListener({
            if (canLoadMore) {
                getPresenter()?.getFirstArticleByTimeList(currentPage)
            }
        }, first_project_rv)


        first_project_refresh.setOnRefreshListener {
            currentPage = 0
            canLoadMore = true
            getPresenter()?.getFirstArticleByTimeList(currentPage)
        }


        getPresenter()?.getFirstArticleByTimeList(currentPage)
    }

    override fun getFirstArticleByTimeListSuccess(data: ArticleList) {
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


    override fun getFirstArticleListSuccess(articleList: ArticleList) {
    }


    override fun getFirstBannerSuccess(data: List<FirstBannerBean>) {
    }

    override fun getFirstArticleTopSuccess(data: List<ArticleBean>) {

    }

    override fun showLoadingDialog(msg: String) {
    }

    override fun dismissLoadingDialog() {
    }

    override fun onResponseError(msg: String?) {
    }

    override fun createView(): IFirstView = this

    override fun createPresenter(): FirstPresenter = FirstPresenter()

    override fun getLayoutId(): Int = R.layout.fragment_new_project

    override fun init() {}
}
