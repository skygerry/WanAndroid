package com.gerry.wanandroidmvvm.first.newarticle

import com.gerry.basemvvm.base.BaseViewModel
import com.gerry.basemvvm.event.SingleLiveEvent
import com.gerry.wanandroidmvvm.http.bean.ArticleList
import com.gerry.wanandroidmvvm.http.bean.FirstBannerBean
import com.gerry.wanandroidmvvm.http.WanAndroidRepository
import kotlinx.coroutines.async

class NewArticleViewModel : BaseViewModel() {
    private val firstBanner = SingleLiveEvent<List<FirstBannerBean>>()

    private val articleData = SingleLiveEvent<ArticleList>()

    fun getFirstBanner(): SingleLiveEvent<List<FirstBannerBean>> {
//        launchGo({
//            val result = WanAndroidRepository.getFirstBanner()
//            if (result.isSuccess()) {
//                firstBanner.value = result.data
//            }
//        })

        launchOnlyData({
            WanAndroidRepository.getFirstBanner()
        }, {
            firstBanner.value = it
        })
        return firstBanner
    }

    fun getFirstArticleTopAndList(page: Int): SingleLiveEvent<ArticleList> {
        launchGo({
            val top = async { WanAndroidRepository.getFirstArticleTop() }
            val list = async { WanAndroidRepository.getFirstArticleList(page) }

            var articleList = ArticleList()
            val firstArticleList = list.await().data
            if (firstArticleList != null) {
                articleList = firstArticleList
            }
            val topArticles = top.await().data
            if (!topArticles.isNullOrEmpty()) {
                articleList.datas.addAll(0, topArticles)
            }

            articleData.value = articleList
        })
        return articleData
    }

    fun getFirstArticleList(page: Int): SingleLiveEvent<ArticleList> {
        launchOnlyData({
            WanAndroidRepository.getFirstArticleList(page)
        }, {
            articleData.value = it
        })
        return articleData
    }

}
