package com.gerry.wanandroidmvvm.project

import com.gerry.basemvvm.base.BaseViewModel
import com.gerry.basemvvm.event.SingleLiveEvent
import com.gerry.wanandroid.http.bean.TreeBean
import com.gerry.wanandroidmvvm.http.WanAndroidRepository

class ProjectViewModel:BaseViewModel() {
    private val projectData = SingleLiveEvent<List<TreeBean>>()

    fun getProjectTree(): SingleLiveEvent<List<TreeBean>> {
        launchOnlyData({
            WanAndroidRepository.getProjectTree()
        },{
            projectData.value = it
        })
        return projectData
    }
}