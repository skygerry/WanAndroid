package com.gerry.wanandroidmvvm.wxofficial

import com.gerry.basemvvm.base.BaseViewModel
import com.gerry.basemvvm.event.SingleLiveEvent
import com.gerry.wanandroid.http.bean.TreeBean
import com.gerry.wanandroidmvvm.http.WanAndroidRepository

class WxOfficialViewModel:BaseViewModel() {
    private val wxOfficialData = SingleLiveEvent<List<TreeBean>>()

    fun getWxArticleChapters(): SingleLiveEvent<List<TreeBean>> {
        launchOnlyData({
            WanAndroidRepository.getWxArticleChapters()
        },{
            wxOfficialData.value = it
        })
        return wxOfficialData
    }
}