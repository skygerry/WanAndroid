package com.gerry.wanandroidmvvm.http.bean

import com.gerry.basemvvm.base.IBaseResponse


/**
 * {
 *  "data": ...,
 *  "errorCode": 0,
 *  "errorMsg": ""
 *  }
 */
data class ResultData<T>(var data: T, var errorCode: Int, var errorMsg: String) : IBaseResponse<T> {
    override fun code(): Int = errorCode

    override fun msg(): String = errorMsg

    override fun data(): T = data

    override fun isSuccess(): Boolean = errorCode == 0

}