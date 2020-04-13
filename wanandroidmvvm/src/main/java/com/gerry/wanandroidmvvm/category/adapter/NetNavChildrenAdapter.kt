package com.gerry.wanandroidmvvm.category.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.gerry.wanandroid.http.bean.ArticleBean
import com.gerry.wanandroidmvvm.R

class NetNavChildrenAdapter(layoutResId: Int) :
    BaseQuickAdapter<ArticleBean, BaseViewHolder>(layoutResId) {

    override fun convert(helper: BaseViewHolder, item: ArticleBean?) {
        helper.setText(R.id.system_children_name_tv, item?.title)
    }
}