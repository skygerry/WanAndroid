package com.gerry.wanandroidmvvm.first.adapter

import android.text.TextUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.gerry.wanandroid.http.bean.ArticleBean
import com.gerry.wanandroidmvvm.R

class NewArticleAdapter :
    BaseQuickAdapter<ArticleBean, BaseViewHolder>(R.layout.item_article_new) {
    override fun convert(helper: BaseViewHolder, item: ArticleBean?) {
        helper.setText(R.id.article_new_name_tv, item?.title)

        if (!TextUtils.isEmpty(item?.author)) {
            helper.setText(R.id.article_author_tv, "作者：${item?.author}")
        } else {
            if (!TextUtils.isEmpty(item?.shareUser)) {
                helper.setText(R.id.article_author_tv, "分享人：${item?.shareUser}")
            }
        }


        var tag = ""
        if (!TextUtils.isEmpty(item?.superChapterName)) {
            tag = item?.superChapterName!! + "/"
        }
        if (!TextUtils.isEmpty(item?.chapterName)) {
            tag += item?.chapterName
        }
        helper.setText(R.id.article_tag_tv, "分类：$tag")

        helper.setText(R.id.article_time_tv, "时间：${item?.niceDate}")
    }
}