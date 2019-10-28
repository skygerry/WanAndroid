package com.gerry.wanandroid.first.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.gerry.wanandroid.R
import com.gerry.wanandroid.http.bean.ArticleBean

class NewArticleAdapter(layoutResId: Int) :
    BaseQuickAdapter<ArticleBean, BaseViewHolder>(layoutResId) {
    override fun convert(helper: BaseViewHolder, item: ArticleBean?) {
        helper.setText(R.id.article_new_name_tv, item?.title)
    }
}