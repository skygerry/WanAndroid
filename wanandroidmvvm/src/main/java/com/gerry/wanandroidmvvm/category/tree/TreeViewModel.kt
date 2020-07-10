package com.gerry.wanandroidmvvm.category.tree

import com.gerry.basemvvm.base.BaseViewModel
import com.gerry.basemvvm.event.SingleLiveEvent
import com.gerry.wanandroidmvvm.http.bean.TreeBean
import com.gerry.wanandroidmvvm.http.WanAndroidRepository

class TreeViewModel : BaseViewModel() {
    private val treeData = SingleLiveEvent<List<TreeBean>>()

    fun getSystemTree(): SingleLiveEvent<List<TreeBean>> {
        launchOnlyData({
            WanAndroidRepository.getTree()
        }, {
            treeData.value = it
        })
        return treeData
    }
}