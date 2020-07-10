package com.gerry.wanandroidmvvm.category.systemarticle

import com.gerry.basemvvm.base.BaseViewModel
import com.gerry.basemvvm.event.SingleLiveEvent
import com.gerry.wanandroidmvvm.http.bean.ArticleList
import com.gerry.wanandroidmvvm.http.WanAndroidRepository

class SystemArticleViewModel:BaseViewModel() {
    private val systemArticleData = SingleLiveEvent<ArticleList>()

    fun getArticleByCid(page: Int, cid: Int): SingleLiveEvent<ArticleList> {
        launchOnlyData({
            WanAndroidRepository.getTreeArticle(page,cid)
        },{
            systemArticleData.value  = it
        })
        return systemArticleData
    }
}