package com.gerry.wanandroidmvvm.category.adapter

import android.text.TextUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.gerry.wanandroidmvvm.http.bean.ArticleBean
import com.gerry.wanandroidmvvm.R

class SystemArticleAdapter :
    BaseQuickAdapter<ArticleBean, BaseViewHolder>(R.layout.item_article_system) {
    override fun convert(helper: BaseViewHolder, item: ArticleBean?) {
        helper.setText(R.id.article_system_name_tv, item?.title)

        if (!TextUtils.isEmpty(item?.author)) {
            helper.setText(R.id.article_author_tv, "作者：${item?.author}")
        } else {
            if (!TextUtils.isEmpty(item?.shareUser)) {
                helper.setText(R.id.article_author_tv, "分享人：${item?.shareUser}")
            }
        }

        helper.setText(R.id.article_time_tv, "时间：${item?.niceDate}")
    }
}