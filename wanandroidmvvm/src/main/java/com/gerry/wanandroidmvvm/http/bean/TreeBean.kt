package com.gerry.wanandroidmvvm.http.bean
import com.google.gson.annotations.SerializedName


data class TreeBean(
    @SerializedName("children")
    var children: List<Children> = listOf(),
    @SerializedName("courseId")
    var courseId: Int = 0,
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("name")
    var name: String = "",
    @SerializedName("order")
    var order: Int = 0,
    @SerializedName("parentChapterId")
    var parentChapterId: Int = 0,
    @SerializedName("userControlSetTop")
    var userControlSetTop: Boolean = false,
    @SerializedName("visible")
    var visible: Int = 0
)

data class Children(
    @SerializedName("children")
    var children: List<Any> = listOf(),
    @SerializedName("courseId")
    var courseId: Int = 0,
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("name")
    var name: String = "",
    @SerializedName("order")
    var order: Int = 0,
    @SerializedName("parentChapterId")
    var parentChapterId: Int = 0,
    @SerializedName("userControlSetTop")
    var userControlSetTop: Boolean = false,
    @SerializedName("visible")
    var visible: Int = 0
)