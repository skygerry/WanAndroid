package com.gerry.wanandroid.first.adapter

import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.gerry.wanandroid.R
import com.gerry.wanandroid.http.bean.ArticleBean

class ProjectAdapter(layoutResId: Int) :
    BaseQuickAdapter<ArticleBean, BaseViewHolder>(layoutResId) {
    override fun convert(helper: BaseViewHolder, item: ArticleBean?) {
        helper.setText(R.id.project_name_item_iv, item?.title)

        helper.setText(R.id.project_author_tv, item?.author)
        helper.setText(R.id.project_time_tv, item?.niceDate)

        Glide.with(mContext)
            .load(item?.envelopePic)
            .into(helper.getView(R.id.project_item_iv))
    }
}