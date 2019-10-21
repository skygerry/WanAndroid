package com.gerry.wanandroid.http.exception

class ApiException : Exception {
    var code: Int = 0
    var displayMessage: String? = null

    constructor(code: Int, displayMessage: String) {
        this.code = code
        this.displayMessage = displayMessage
    }

    constructor(code: Int, message: String, displayMessage: String) : super(message) {
        this.code = code
        this.displayMessage = displayMessage
    }
}