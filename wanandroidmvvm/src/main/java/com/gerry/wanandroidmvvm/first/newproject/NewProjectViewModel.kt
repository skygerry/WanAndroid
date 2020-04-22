package com.gerry.wanandroidmvvm.first.newproject

import com.gerry.basemvvm.base.BaseViewModel
import com.gerry.basemvvm.event.SingleLiveEvent
import com.gerry.wanandroid.http.bean.ArticleList
import com.gerry.wanandroidmvvm.http.WanAndroidRepository

class NewProjectViewModel : BaseViewModel() {
    private val projectData = SingleLiveEvent<ArticleList>()

    fun getFirstArticleByTimeList(page: Int): SingleLiveEvent<ArticleList> {
        launchOnlyData({
            WanAndroidRepository.getFirstArticleListByTime(page)
        },{
            projectData.value = it
        })
        return projectData
    }

}
