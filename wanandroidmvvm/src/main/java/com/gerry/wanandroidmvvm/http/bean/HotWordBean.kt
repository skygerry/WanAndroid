package com.gerry.wanandroidmvvm.http.bean
import com.google.gson.annotations.SerializedName


data class HotWordBean(
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("link")
    var link: String = "",
    @SerializedName("name")
    var name: String = "",
    @SerializedName("order")
    var order: Int = 0,
    @SerializedName("visible")
    var visible: Int = 0
)