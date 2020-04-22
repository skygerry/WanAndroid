package com.gerry.wanandroidmvvm.mine


import android.os.Bundle
import com.gerry.basemvvm.base.BaseFragment

import com.gerry.wanandroidmvvm.R

class MineFragment : BaseFragment<MineViewModel>() {

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
    }

    override fun lazyLoadData() {
        super.lazyLoadData()
    }
    override fun getLayoutId(): Int = R.layout.fragment_mine
}