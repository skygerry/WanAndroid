package com.gerry.wanandroid.base

interface BaseView {
    //显示dialog
    fun showLoadingDialog(msg: String)

    //取消dialog
    fun dismissLoadingDialog()
}