package com.gerry.wanandroidmvvm.base.view

interface BaseView {
    //显示dialog
    fun showLoadingDialog(msg: String)

    //取消dialog
    fun dismissLoadingDialog()

    fun onResponseError(msg: String?)
}