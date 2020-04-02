package com.gerry.wanandroid.category.adapter

import androidx.core.content.ContextCompat
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.gerry.wanandroid.R
import com.gerry.wanandroid.http.bean.Children

class SystemChildrenAdapter(layoutResId: Int) :
    BaseQuickAdapter<Children, BaseViewHolder>(layoutResId) {

    override fun convert(helper: BaseViewHolder, item: Children?) {
        helper.setText(R.id.system_children_name_tv, item?.name)
    }
}