package com.gerry.wanandroidmvvm.project.content

import com.gerry.basemvvm.base.BaseViewModel
import com.gerry.basemvvm.event.SingleLiveEvent
import com.gerry.wanandroid.http.bean.ArticleList
import com.gerry.wanandroidmvvm.http.WanAndroidRepository

class ProjectContentViewModel : BaseViewModel() {
    private val contentData = SingleLiveEvent<ArticleList>()
    fun getProjectArticle(page: Int, cid: Int): SingleLiveEvent<ArticleList> {
        launchOnlyData({
            WanAndroidRepository.getProjectListByCid(page, cid)
        }, {
            contentData.value = it
        })
        return contentData
    }
}