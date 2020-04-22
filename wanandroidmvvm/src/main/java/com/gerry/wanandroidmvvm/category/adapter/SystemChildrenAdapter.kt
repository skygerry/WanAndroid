package com.gerry.wanandroidmvvm.category.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.gerry.wanandroid.http.bean.Children
import com.gerry.wanandroidmvvm.R

class SystemChildrenAdapter :
    BaseQuickAdapter<Children, BaseViewHolder>(R.layout.item_system_children) {

    override fun convert(helper: BaseViewHolder, item: Children?) {
        helper.setText(R.id.system_children_name_tv, item?.name)
    }
}