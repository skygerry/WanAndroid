package com.gerry.basemvvm.network

import com.gerry.basemvvm.base.IBaseResponse
import com.gerry.wanandroidmvvm.basemvvm.network.ERROR

class ApiException : Exception {
    var code: Int = 0
    var errorMsg: String? = null

    constructor(error: ERROR, e: Throwable? = null) : super(e) {
        code = error.getKey()
        errorMsg = error.getValue()
    }

    constructor(code: Int, msg: String, e: Throwable? = null) : super(e) {
        this.code = code
        this.errorMsg = msg
    }

    constructor(base: IBaseResponse<*>, e: Throwable? = null) : super(e) {
        this.code = base.code()
        this.errorMsg = base.msg()
    }
}