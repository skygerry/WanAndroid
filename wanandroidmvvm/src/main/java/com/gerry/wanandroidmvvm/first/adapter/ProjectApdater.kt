package com.gerry.wanandroidmvvm.first.adapter

import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.gerry.wanandroidmvvm.http.bean.ArticleBean
import com.gerry.wanandroidmvvm.R

class ProjectAdapter :
    BaseQuickAdapter<ArticleBean, BaseViewHolder>(R.layout.item_project) {
    override fun convert(helper: BaseViewHolder, item: ArticleBean?) {
        helper.setText(R.id.project_name_item_iv, item?.title)

        helper.setText(R.id.project_author_tv, item?.author)
        helper.setText(R.id.project_time_tv, item?.niceDate)

        Glide.with(mContext)
            .load(item?.envelopePic)
            .into(helper.getView(R.id.project_item_iv))
    }
}