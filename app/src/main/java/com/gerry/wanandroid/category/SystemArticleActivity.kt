package com.gerry.wanandroid.category

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.gerry.wanandroid.R
import com.gerry.wanandroid.base.activity.BaseActivity
import com.gerry.wanandroid.category.adapter.SystemArticleAdapter
import com.gerry.wanandroid.category.mvp.ISystemView
import com.gerry.wanandroid.category.mvp.SystemPresenter
import com.gerry.wanandroid.http.bean.ArticleBean
import com.gerry.wanandroid.http.bean.ArticleList
import com.gerry.wanandroid.http.bean.NaviBean
import com.gerry.wanandroid.http.bean.TreeBean
import com.gerry.wanandroid.web.CommentWebActivity
import kotlinx.android.synthetic.main.activity_system_article.*

class SystemArticleActivity : BaseActivity<ISystemView, SystemPresenter>(), ISystemView {

    var articleList = mutableListOf<ArticleBean>()
    lateinit var systemArticleAdapter: SystemArticleAdapter

    var currentPage = 0

    var cid = 0

    var title = ""

    var canLoadMore = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (intent != null) {
            cid = intent.getIntExtra("cid", 0)
            if (intent.getStringExtra("title") != null) {
                title = intent.getStringExtra("title")
            }
        }
        initView()

        getPresenter()?.getArticleByCid(currentPage, cid)
    }

    private fun initView() {
        system_title_tv.text = title

        systemArticleAdapter = SystemArticleAdapter(R.layout.item_article_system)

        systemArticleAdapter.onItemClickListener =
            BaseQuickAdapter.OnItemClickListener { adapter, view, position ->
                var intent = Intent(this, CommentWebActivity::class.java)
                intent.putExtra("url", articleList[position].link)
                startActivity(intent)
            }
        system_article_rv.layoutManager = LinearLayoutManager(this)
        system_article_rv.adapter = systemArticleAdapter

        systemArticleAdapter.setOnLoadMoreListener({
            if (canLoadMore) {
                getPresenter()?.getArticleByCid(currentPage, cid)
            }
        }, system_article_rv)


        system_back_iv.setOnClickListener {
            finish()
        }
    }

    override fun getArticleListSuccess(data: ArticleList) {
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

    override fun getSystemTreeSuccess(data: List<TreeBean>) {}
    override fun getNaviDataSuccess(data: List<NaviBean>) {}

    override fun showLoadingDialog(msg: String) {}
    override fun dismissLoadingDialog() {}
    override fun onResponseError(msg: String?) {}

    override fun createView(): ISystemView = this
    override fun createPresenter(): SystemPresenter = SystemPresenter()
    override fun getLayoutId(): Int = R.layout.activity_system_article


}
