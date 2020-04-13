package com.gerry.wanandroid.http.bean


/**
 * {
 *  "data": ...,
 *  "errorCode": 0,
 *  "errorMsg": ""
 *  }
 */
data class ResultData<T>(var data: T, var errorCode: Int, var errorMsg: String)