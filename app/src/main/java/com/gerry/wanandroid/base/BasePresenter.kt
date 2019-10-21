package com.gerry.wanandroid.base


open class BasePresenter<V> {
    var view: V? = null

    //默认初始化
    fun attachView(v: V) {
        this.view = v
    }

    //Activity关闭把view对象置为空
    fun detachView() {
        if (view != null) {
            view = null
        }
    }


}