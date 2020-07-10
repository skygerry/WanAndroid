package com.gerry.wanandroidmvvm.category.netnav

import com.gerry.basemvvm.base.BaseViewModel
import com.gerry.basemvvm.event.SingleLiveEvent
import com.gerry.wanandroidmvvm.http.bean.NaviBean
import com.gerry.wanandroidmvvm.http.WanAndroidRepository

class NetNavViewModel : BaseViewModel() {
    private val naviData = SingleLiveEvent<List<NaviBean>>()

    fun getNaviData(): SingleLiveEvent<List<NaviBean>> {
        launchOnlyData({
            WanAndroidRepository.getNaviData()
        }, {
            naviData.value = it
        })
        return naviData
    }
}