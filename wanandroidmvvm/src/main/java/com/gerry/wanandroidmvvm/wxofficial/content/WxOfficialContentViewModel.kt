package com.gerry.wanandroidmvvm.wxofficial.content

import com.gerry.basemvvm.base.BaseViewModel
import com.gerry.basemvvm.event.SingleLiveEvent
import com.gerry.wanandroidmvvm.http.bean.ArticleList
import com.gerry.wanandroidmvvm.http.WanAndroidRepository

class WxOfficialContentViewModel : BaseViewModel() {
    private val contentData = SingleLiveEvent<ArticleList>()
    fun getWxArticleList(cid: Int, page: Int): SingleLiveEvent<ArticleList> {
        launchOnlyData({
            WanAndroidRepository.getWxArticleList(cid, page)
        }, {
            contentData.value = it
        })
        return contentData
    }
}