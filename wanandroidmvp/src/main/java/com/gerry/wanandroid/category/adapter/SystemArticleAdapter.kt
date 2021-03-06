package com.gerry.wanandroid.category.adapter

import android.text.TextUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.gerry.wanandroid.R
import com.gerry.wanandroid.http.bean.ArticleBean

class SystemArticleAdapter(layoutResId: Int) :
    BaseQuickAdapter<ArticleBean, BaseViewHolder>(layoutResId) {
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