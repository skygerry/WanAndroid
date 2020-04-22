package com.gerry.wanandroidmvvm.first.newarticle


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.gerry.basemvvm.base.BaseFragment
import com.gerry.wanandroidmvvm.first.adapter.NewArticleAdapter
import com.gerry.wanandroid.http.bean.ArticleBean
import com.gerry.wanandroid.http.bean.ArticleList
import com.gerry.wanandroid.http.bean.FirstBannerBean
import com.gerry.wanandroidmvvm.R
import com.gerry.wanandroidmvvm.category.netnav.NetNavViewModel
import com.gerry.wanandroidmvvm.web.CommentWebActivity
import com.youth.banner.Banner
import kotlinx.android.synthetic.main.fragment_new_article.*

class NewArticleFragment : BaseFragment<NewArticleViewModel>() {
    private var articleList = mutableListOf<ArticleBean>()
    private val newArticleAdapter by lazy { NewArticleAdapter() }

    private var currentPage = 0

    var canLoadMore = true


    override fun initView(savedInstanceState: Bundle?) {
        with(first_new_rv) {
            layoutManager = LinearLayoutManager(context)
            adapter = newArticleAdapter
        }
        newArticleAdapter.apply {
            setOnItemClickListener { adapter, _, position ->
                val article = adapter.data[position] as ArticleBean
                val intent = Intent(context, CommentWebActivity::class.java)
                intent.putExtra("url", article.link)
                startActivity(intent)
            }

            setOnLoadMoreListener({
                if (canLoadMore) {
                    viewModel.getFirstArticleList(currentPage)
                }
            }, first_new_rv)
        }

        newArticleAdapter.setOnLoadMoreListener({
            if (canLoadMore) {
                viewModel.getFirstArticleList(currentPage)
            }
        }, first_new_rv)

        first_new_refresh.setOnRefreshListener {
            currentPage = 0
            canLoadMore = true
            viewModel.getFirstArticleTopAndList(currentPage)
        }


        viewModel.getFirstArticleTopAndList(currentPage)
    }

    override fun lazyLoadData() {
        viewModel.run {
            getFirstBanner().observe(this@NewArticleFragment, Observer {
                getFirstBannerSuccess(it)
            })

            getFirstArticleTopAndList(currentPage).observe(this@NewArticleFragment, Observer {
                getFirstArticleListSuccess(it)
            })

            getFirstArticleList(currentPage).observe(this@NewArticleFragment, Observer {
                getFirstArticleListSuccess(it)
            })
        }
    }


    private fun getFirstArticleListSuccess(articleList: ArticleList) {
        if (articleList != null) {
            if (currentPage == 0) {
                this.articleList.clear()
            }
            this.articleList.addAll(articleList.datas)
            newArticleAdapter.setNewData(this.articleList)
            if (articleList.curPage < articleList.pageCount) {
                currentPage++
                canLoadMore = true
                newArticleAdapter.loadMoreComplete()
            } else {
                canLoadMore = false
                newArticleAdapter.loadMoreEnd()
            }
            first_new_refresh.isRefreshing = false

        }
        Log.e("----->", articleList.datas.size.toString())
    }

    private fun getFirstBannerSuccess(data: List<FirstBannerBean>) {
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
                var intent = Intent(context, CommentWebActivity::class.java)
                intent.putExtra("url", data[position - 1].url)
                startActivity(intent)
            }
        }

        newArticleAdapter.addHeaderView(headerView)
    }

    override fun getLayoutId(): Int = R.layout.fragment_new_article

    override fun createViewModel(): NewArticleViewModel {
        return ViewModelProvider(this).get(NewArticleViewModel::class.java)
    }
}
