package com.gerry.wanandroidmvvm.mine


import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.gerry.basemvvm.base.BaseFragment

import com.gerry.wanandroidmvvm.R
import com.gerry.wanandroidmvvm.category.netnav.NetNavViewModel

class MineFragment : BaseFragment<MineViewModel>() {

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
    }

    override fun lazyLoadData() {
        super.lazyLoadData()
    }
    override fun getLayoutId(): Int = R.layout.fragment_mine

    override fun createViewModel(): MineViewModel {
        return ViewModelProvider(this).get(MineViewModel::class.java)
    }
}