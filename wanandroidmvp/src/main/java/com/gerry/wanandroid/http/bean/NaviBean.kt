package com.gerry.wanandroid.http.bean
import com.google.gson.annotations.SerializedName


data class NaviBean(
    @SerializedName("articles")
    var articles: List<ArticleBean> = listOf(),
    @SerializedName("cid")
    var cid: Int = 0,
    @SerializedName("name")
    var name: String = ""
)