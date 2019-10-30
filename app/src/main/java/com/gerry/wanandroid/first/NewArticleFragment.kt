package com.gerry.wanandroid.first


import android.content.Context
import android.content.Intent
import android.os.Build.ID
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.gerry.wanandroid.R
import com.gerry.wanandroid.base.fragement.BaseFragment
import com.gerry.wanandroid.first.adapter.NewArticleAdapter
import com.gerry.wanandroid.first.mvp.FirstPresenter
import com.gerry.wanandroid.first.mvp.IFirstView
import com.gerry.wanandroid.http.bean.ArticleBean
import com.gerry.wanandroid.http.bean.ArticleList
import com.gerry.wanandroid.http.bean.FirstBannerBean
import com.gerry.wanandroid.web.CommentWebActivity
import com.youth.banner.Banner
import com.youth.banner.listener.OnBannerClickListener
import com.youth.banner.loader.ImageLoader
import kotlinx.android.synthetic.main.fragment_new_article.*

class NewArticleFragment : BaseFragment<IFirstView, FirstPresenter>(), IFirstView {
    var articleList = mutableListOf<ArticleBean>()
    lateinit var newArticleAdapter: NewArticleAdapter

    var currentPage = 0

    var canLoadMore = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newArticleAdapter = NewArticleAdapter(R.layout.item_article_new)

        newArticleAdapter.onItemClickListener =
            BaseQuickAdapter.OnItemClickListener { adapter, view, position ->
                var intent = Intent(mContext, CommentWebActivity::class.java)
                intent.putExtra("url", articleList[position].link)
                startActivity(intent)
            }
        first_new_rv.layoutManager = LinearLayoutManager(mContext)
        first_new_rv.adapter = newArticleAdapter

        newArticleAdapter.setOnLoadMoreListener({
            if (canLoadMore) {
                getPresenter()?.getFirstArticleList(currentPage)
            }
        }, first_new_rv)

        first_new_refresh.setOnRefreshListener {
            currentPage = 0
            canLoadMore = true
            getPresenter()?.getFirstArticleTopAndList(currentPage)
        }


        getPresenter()?.getFirstBanner()

        getPresenter()?.getFirstArticleTopAndList(currentPage)
    }


    override fun getFirstArticleListSuccess(articleList: ArticleList) {
        if (articleList != null) {
            if (currentPage == 0) {
                this.articleList.clear()
            }
            this.articleList.addAll(articleList.datas)

            if (articleList.curPage < articleList.pageCount) {
                currentPage++
                canLoadMore = true
                newArticleAdapter.loadMoreComplete()
            } else {
                canLoadMore = false
                newArticleAdapter.loadMoreEnd()
            }
            first_new_refresh.isRefreshing = false
            newArticleAdapter.setNewData(this.articleList)
        }
        Log.e("----->", articleList.datas.size.toString())
    }

    override fun getFirstArticleByTimeListSuccess(data: ArticleList) {
    }

    override fun getFirstBannerSuccess(data: List<FirstBannerBean>) {
        newArticleAdapter.removeAllHeaderView()
        var urlList = mutableListOf<String>()
        if (!data.isNullOrEmpty()) {
            for (i in data) {
                urlList.add(i.imagePath)
            }
        }
        var headerView = layoutInflater.inflate(R.layout.layout_first_header, null, false)
        var banner = headerView.findViewById(R.id.first_banner) as Banner

        banner.setImageLoader { context, path, imageView ->
            val roundedCorners = RoundedCorners(20)
            val bitmapTransform = RequestOptions.bitmapTransform(roundedCorners)
            Glide.with(context!!).load(path).apply(bitmapTransform).into(imageView!!)
        }
        banner.setImages(urlList)
            .isAutoPlay(true)
            .start()

        banner.setOnBannerClickListener { position ->
            if (data != null) {
                var intent = Intent(mContext, CommentWebActivity::class.java)
                intent.putExtra("url", data[position - 1].url)
                startActivity(intent)
            }
        }

        newArticleAdapter.addHeaderView(headerView)
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

    override fun getLayoutId(): Int = R.layout.fragment_new_article

    override fun init() {}
}
